# Week 0 - Kickoff

## Primary objectives

* Common file system terminology: files, folders, and paths
* **Navigate a hierarchical file system using `ls`, `cd`, and `pwd`**
* Describe the purpose of source control
* Describe the different between local repository and remote repository
* Explain the terms clone, commit, push, and pull
* **Use common git commands: `clone`, `status`, `add`, `commit`, `pull`, and `push`**

### Notes for the instructor

The structure of this day is different than the rest of the program. Students may have less context about the day's learning objectives and need additional guidance on where they can go to self-study as a follow-up.

Add the first week's exercises and tutorials to the `upstream` repository before class begins so that you and your students can verify that when they run `git pull upstream main` they see new materials.

## Objective 1: Navigate a hierarchical file system using ls, cd, and pwd

### Opening

Computers store and organize information in a file system using files and folders. File systems organize the contents hierarchically as a _tree structure_.

Programmers need to develop proficiency navigating the file system with GUI-based programs like Windows Explorer and also command-line programs like Windows Terminal. Many of the newer frameworks they'll work with leverage a command-line based interface.

The most common commands are: `ls`, `cd`, and `pwd`.

Some common difficulties when learning and navigating the file system are:
* knowing their current working directory
* navigating through a directory hierarchy, including parent, sub, and adjacent directories
* absolute versus relative paths
* home directory is dependent on the current user
* "Library folders" such as `Desktop`, `Documents`, and `Downloads`
* files and folders without names such as `.gitignore`, `.idea`

### Example

Run the provided shell file `sh lecture.sh` to generate a directory hierarchy in your home directory that matches [this image](folders.png). Each folder contains at least one text file.

Pick a random starting point as your working directory and ask students what commands you need to type to navigate to another target folder or file. Include a combination of absolute and relative paths so that your students can become more confident that they can navigate the directories on their computers.

## Objective 2: Use common git commands: clone, status, add, commit, pull, and push

### Opening

Students need to understand common Git commands to work through their course material. These commands help them copy a repository to work on it locally, track their changes, save their changes, and synchronize their local repository with another remote repository.

The six commands students need to understand are: `clone`, `status`, `add`, `commit`, `pull`, and `push`.

Some common difficulties students encounter when learning git include:
* remembering to `add`, `commit` and `push`
* pushing or pulling while there are untracked changes
* encountering issues when pulling new code into their repository
* local vs. remote
* origin vs. upstream

### Next steps

Have students navigate to their student repository and run `git pull upstream main`.

Assuming that this works for everyone, take time to explain to students that they pulled in the latest changes from the instructor repository. Walk them through the new folders that show up for the week and draw their attention to the new `resources`, `exercise`, and `tutorial` folders.