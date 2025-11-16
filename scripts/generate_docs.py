#!/usr/bin/env python3
"""Generate docs/index.html listing all .java files in the repository.

This script scans the repository (excluding common build and metadata
directories) for `.java` files and generates `docs/index.html` with links to
those files on GitHub (branch `main`).

Run locally:
  python scripts/generate_docs.py
"""
from pathlib import Path
from datetime import datetime
import os

REPO_OWNER = "EchoSingh"
REPO_NAME = "INS-Lab"
BRANCH = "main"

ROOT = Path(__file__).resolve().parents[1]
DOCS_DIR = ROOT / "docs"
EXCLUDE_DIRS = {"docs", ".git", ".github", "build", "out", "target", ".idea", ".vscode"}


def find_java_files(root: Path):
    files = []
    for p in root.rglob("*.java"):
        # skip files in excluded directories
        if any(part in EXCLUDE_DIRS for part in p.parts):
            continue
        files.append(p.relative_to(root).as_posix())
    files.sort()
    return files


def github_blob_url(path: str) -> str:
    return f"https://github.com/{REPO_OWNER}/{REPO_NAME}/blob/{BRANCH}/{path}"


def render_html(java_files):
    now = datetime.utcnow().isoformat() + "Z"
    title = "INS-Lab — Information Security Lab Programs"
    css_path = "style.css"
    lines = []
    lines.append("<!doctype html>")
    lines.append("<html lang=\"en\">")
    lines.append("<head>")
    lines.append("  <meta charset=\"utf-8\">")
    lines.append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">")
    lines.append(f"  <title>{title}</title>")
    lines.append(f"  <link rel=\"stylesheet\" href=\"{css_path}\">")
    lines.append("</head>")
    lines.append("<body>")
    lines.append("  <header>")
    lines.append("    <h1>INS-Lab</h1>")
    lines.append("    <p>Information Security lab programs by <strong>EchoSingh</strong>.</p>")
    lines.append("  </header>")
    lines.append("  <main>")
    lines.append("    <section>")
    lines.append("      <h2>Programs (Java)</h2>")
    lines.append("      <ul>")
    if not java_files:
        lines.append("        <li>No .java files found in the repository.</li>")
    else:
        for path in java_files:
            blob = github_blob_url(path)
            name = Path(path).name
            lines.append(f"        <li><a href=\"{blob}\">{name}</a> — <code>{path}</code></li>")
    lines.append("      </ul>")
    lines.append("      <p>Files are scanned from the repository root and linked to their GitHub blob URL.</p>")
    lines.append("    </section>")
    lines.append("    <section>")
    lines.append("      <h2>How to run locally</h2>")
    lines.append("      <pre>javac XorString.java\njava XorString &lt; inputf.in</pre>")
    lines.append("    </section>")
    lines.append("  </main>")
    lines.append("  <footer>")
    lines.append(f"    <p>Auto-generated: {now}</p>")
    lines.append(f"    <p>Repo: <a href=\"https://github.com/{REPO_OWNER}/{REPO_NAME}\">https://github.com/{REPO_OWNER}/{REPO_NAME}</a></p>")
    lines.append("  </footer>")
    lines.append("</body>")
    lines.append("</html>")
    return "\n".join(lines)


def main():
    java_files = find_java_files(ROOT)
    html = render_html(java_files)

    DOCS_DIR.mkdir(parents=True, exist_ok=True)
    out_file = DOCS_DIR / "index.html"
    out_file.write_text(html, encoding="utf-8")
    print(f"Wrote {out_file} ({len(java_files)} java files)")


if __name__ == "__main__":
    main()
