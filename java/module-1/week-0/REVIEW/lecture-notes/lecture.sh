#!/bin/bash

# This script creates a folder hierarchy that matches __resources/folders.png 
# in the user's home directory.

echo
read -r -p "Enter a top-directory: " topdir

echo
echo "Creating top-level directory at ~/${topdir}"

# Create the top-level directory
cd ~
rm -rf ${topdir}
mkdir ${topdir}
cd ${topdir}

# Create the organization
mkdir tech-studio-inc
cd tech-studio-inc

# Create the departments
mkdir accounting
mkdir accounting/expenses

mkdir clients
mkdir clients/elevator-tech-service
mkdir clients/professional-pathways
mkdir clients/youstrong

mkdir engineering
mkdir engineering/project-1
mkdir engineering/project-2

mkdir hr

# /accounting
printf "This is our general policy statement." >> accounting/general-policy.txt

# /accounting/expenses
printf "expense-01 2022-01-01 \$23.00" >> accounting/expenses/expense-01.txt
printf "expense-02 2022-02-01 \$13.30" >> accounting/expenses/expense-02.txt
printf "expense-03 2022-02-03 \$123.25" >> accounting/expenses/expense-03.txt
printf "expense-04 2022-03-01 \$529.00" >> accounting/expenses/expense-04.txt
printf "expense-05 2022-03-04 \$117.78" >> accounting/expenses/expense-05.txt
printf "expense-06 2022-03-14 \$235.93" >> accounting/expenses/expense-06.txt

# /clients/elevator-tech-service
printf "Elevator Tech Service | John Smith | 234-555-1234" >> clients/elevator-tech-service/contact-info.txt

# /clients/professional-pathways
printf "Pathway Careers | Karl Crowe | 202-555-0122" >> clients/professional-pathways/contact-info.txt

# /clients/youstrong
printf "YouStrong | Jane Doe | 202-555-0126" >> clients/youstrong/contact-info.txt

# /engineering
printf "Engineering team roadmap" >> engineering/roadmap.txt

# /engineering/project-1
printf "Project Name: Prime Seven | Team Lead: Pat Hernandez | Start date: 2022-01-01" >> engineering/project-1/scope.txt
printf "Samuel Gross\nLatoya Harper\nPaul Johnson" >> engineering/project-1/team.txt

# /engineering/project-2
printf "Project Name: Boomerang | Team Lead: Shane Sargent | Start date: 2022-02-14" >> engineering/project-2/scope.txt
printf "Linda Hill\nPaul Johnson\nJason Clark" >> engineering/project-2/team.txt

# /hr
printf "John Smith\nJane Doe\nKarl Crowe\nPat Hernandez\nSamuel Gross\nLatoya Harper\nPaul Johnson\nLinda Hill\nJason Clark" >> hr/employees.txt

echo "Done."