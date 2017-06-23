# Add more of a description
warn("PR message not descriptive enough (should be >25 chars)") if (github.pr_body == nil || github.pr_body.length < 25);

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500