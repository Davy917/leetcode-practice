import json
from pathlib import Path
from datetime import datetime

def to_time(ts):
    if not ts:
        return ""
    try:
        return datetime.fromtimestamp(ts / 1000).strftime("%Y-%m-%d %H:%M:%S")
    except Exception:
        return ""

def extract_assistant_text(response_items):
    texts = []
    for item in response_items or []:
        if not isinstance(item, dict):
            continue
        kind = item.get("kind", "")
        value = item.get("value", "")
        # 排除 thinking / 空字串 / 非字串
        if kind == "thinking":
            continue
        if isinstance(value, str) and value.strip():
            texts.append(value.strip())
    # 你可以改成只取最後一段：texts[-1] if texts else ""
    return "\n\n".join(texts).strip()

def convert_chat_json_to_md(src_path, dst_path):
    src = Path(src_path)
    dst = Path(dst_path)

    data = json.loads(src.read_text(encoding="utf-8"))
    requests = data.get("requests", [])

    lines = []
    lines.append("# Copilot Chat Export")
    lines.append("")
    lines.append(f"- Responder: {data.get('responderUsername', '')}")
    lines.append(f"- Initial Location: {data.get('initialLocation', '')}")
    lines.append("")

    for i, req in enumerate(requests, start=1):
        msg = (req.get("message") or {}).get("text", "").strip()
        ts = to_time(req.get("timestamp"))
        assistant = extract_assistant_text(req.get("response", []))

        lines.append(f"## Conversation {i}")
        if ts:
            lines.append(f"- Time: {ts}")
        rid = req.get("requestId", "")
        if rid:
            lines.append(f"- Request ID: {rid}")
        lines.append("")
        lines.append("### User")
        lines.append(msg if msg else "(empty)")
        lines.append("")
        lines.append("### Copilot")
        lines.append(assistant if assistant else "(no readable response)")
        lines.append("")
        lines.append("---")
        lines.append("")

    dst.write_text("\n".join(lines), encoding="utf-8")
    print(f"Done: {dst}")

if __name__ == "__main__":
    # 依你的檔案名稱調整
    convert_chat_json_to_md(
        "572-is-subtree\chat.json",
        "572-is-subtree\chat.md"
    )
"""
執行方式：
python3.12 convert_chat_json_to_md.py
"""