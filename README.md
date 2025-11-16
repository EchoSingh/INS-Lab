# INS-Lab

INS-Lab contains Information Security (INS) lab programs and examples by EchoSingh.

What you'll find
- Java programs (e.g. `XorString.java`)
- input files (e.g. `inputf.in`)

Quick start
1. Build and run Java programs (Windows PowerShell):

```powershell
javac XorString.java
java XorString < inputf.in
```

2. Add new programs: add sources to the repo and update the `docs/index.html` links.

Publish a project site (GitHub Pages)
- This repository includes a small site in `docs/` that you can publish via GitHub Pages.
- Steps:
  1. Commit and push the repo to GitHub (your repo: `https://github.com/EchoSingh/INS-Lab`).
  2. In the repository Settings → Pages, set Source to `main` branch and folder `/docs`.
  3. Save — GitHub will provide a URL (usually `https://<username>.github.io/INS-Lab/`).

Adding more programs to the site
- Edit `docs/index.html` to add links to new files, or tell me and I can auto-generate the list.

`.gitignore`
- A Java-friendly `.gitignore` is included to keep build artifacts out of the repo.

If you'd like
- I can auto-generate an index of all source files in the repo and add runnable examples to the site.
- I can also create a `gh-pages` branch and configure automatic publishing.

Auto-generation
- A script is included at `scripts/generate_docs.py` that scans the repository for `.java` files and regenerates `docs/index.html` with links to each file on GitHub.
- A GitHub Action is included at `.github/workflows/generate-docs.yml` that runs the script on every push that changes `*.java`. The action will commit the updated `docs/index.html` back to `main`.

Run locally
- To regenerate `docs/index.html` locally after adding or removing `.java` files, run:

```powershell
python scripts/generate_docs.py
```


Contact / Credits
- Author: EchoSingh
