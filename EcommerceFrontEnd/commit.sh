# Initialize git repository only if .git doesn't exist
if [ ! -d ".git" ]; then
    git init
    echo "Initialized a new Git repository."
fi

# Add all changes to git, excluding those specified in .gitignore
git add .

# Prompt for a commit message
echo "Enter commit message:"
read COMMIT_MESSAGE

# Commit changes
git commit -m "$COMMIT_MESSAGE"

# Push changes to the remote repository
git push -u origin main

echo "Changes have been committed and pushed successfully."
