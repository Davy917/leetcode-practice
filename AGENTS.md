# AGENTS Guide

## Project shape (what this repo is)
- This is a collection of **independent LeetCode solutions**, not one deployable app.
- Each problem lives in its own folder: `{id}-{slug}` (example: `234-is-palindrome/`, `560-subarray-sum/`).
- Typical files per problem are `Solution.java` and/or `solution.py` (see `README.md`).
- Some problem folders use numbered/problem-specific filenames or keep extra variants beside the main solution (examples: `019-remove-nth-from-end/Solution19.java`, `1122-relative-sort-array/Solution1122.java`, `707-my-linked-list/P0707_MyLinkedList.java`, `1122-relative-sort-array/Solution1122_test.java`).
- `algo/` and `datastructure/` are algorithm playground/reference implementations, separate from LeetCode problem folders.
- `JavaPractice/`, `GoPractice/`, and `unfinished-solutions/` are also standalone practice/WIP areas; Go appears only in a few isolated files such as `069-my-sqrt/Solution.go` and `GoPractice/main.go`.
- `out/` contains IDE build artifacts; do not use it as source of truth.

## Architectural boundaries
- There is no cross-folder runtime dependency graph; each folder is expected to run standalone.
- Java files are mostly in the default package for problem folders, so class-name collisions are a real risk across Source Roots (`README.md`, "重複類名衝突").
- Helper structures are often defined inline per file (example: `234-is-palindrome/Solution.java` defines `ListNode` as a static inner class).
- Python solutions similarly keep local node/helper classes in-file (example: `234-is-palindrome/solution.py`).

## Build/run workflows used in practice
- Python quick run from repo root:
  - `python .\560-subarray-sum\solution.py`
- Java quick compile/run for one folder:
  - `javac .\234-is-palindrome\Solution.java`
  - `java -cp .\234-is-palindrome Solution234`
- There is no single project-wide test runner configured; verification is usually via local `main` / `__main__` examples.
- IntelliJ module source roots are tracked in `leetcode-practice.iml`; `unfinished-solutions/` uses its own `unfinished-solutions/unfinished-solutions.iml`.

## Code conventions to preserve
- Keep new problem folders named with numeric prefix and slug.
- Prefer keeping each solution self-contained in one file with minimal external imports.
- Java naming is mixed in current repo (`Solution`, `Solution234`, `Solution1122`); match the local folder's existing style instead of forcing one global rename.
- It is normal for a folder to keep scratch, comparison, or topic-specific files next to the main answer (examples: `033-search/033-search-improve.py`, `912-sort-array/CountingSort.java`, `1122-relative-sort-array/Solution1122_test.java`).
- Python files commonly include debug prints and a small executable block under `if __name__ == "__main__":`.
- Comments are frequently bilingual (Chinese/English); preserve existing language style in touched files.

## Editing guidance for AI agents
- When modifying a problem, limit changes to that problem directory unless the user asks for cross-cutting refactors.
- If adding Java helper classes (e.g., `ListNode`), prefer inner classes or unique names to avoid duplicate-class errors across source roots.
- Do not edit `out/production/**`; make source changes in root problem/algo folders only.
- If you add a new Java problem folder, ensure IDE discoverability via Source Root settings in `leetcode-practice.iml` (or `unfinished-solutions/unfinished-solutions.iml` when working in that module) per existing workflow in `README.md`.
- When a folder already contains multiple solution variants or scratch files, update the intended primary file in place instead of consolidating files unless the user asks for cleanup.
- Include a tiny runnable example (`main` or `__main__`) when the surrounding folder already follows that pattern.

