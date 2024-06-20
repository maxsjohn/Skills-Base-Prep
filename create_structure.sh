#!/bin/bash

# Create the directories and files for the Skills-Base-Prep project

# Create the main directory structure
Delete This line before start !
mkdir -p datastructures/src/main/java/com

# Function to create directories and files for a data structure
create_data_structure() {
  local name=$1
  local capitalized_name=$(echo ${name:0:1} | tr 'a-z' 'A-Z')${name:1}
  mkdir -p datastructures/src/main/java/com/$name
  touch datastructures/src/main/java/com/$name/README.md
  touch datastructures/src/main/java/com/$name/Theory.md
  mkdir -p datastructures/src/main/java/com/$name/Applications
  touch datastructures/src/main/java/com/$name/Applications/Problem1.md
  touch datastructures/src/main/java/com/$name/Applications/Problem2.md
  touch datastructures/src/main/java/com/$name/$capitalized_name.java
}

# Data structures
create_data_structure "arrays"
create_data_structure "linkedlists"
create_data_structure "trees"
create_data_structure "graphs"
create_data_structure "heaps"
create_data_structure "hashtables"

# Function to create directories and files for an algorithm
create_algorithm() {
  local name=$1
  local capitalized_name=$(echo ${name:0:1} | tr 'a-z' 'A-Z')${name:1}
  mkdir -p datastructures/src/main/java/com/algorithms/$name
  touch datastructures/src/main/java/com/algorithms/$name/README.md
  touch datastructures/src/main/java/com/algorithms/$name/Theory.md
  mkdir -p datastructures/src/main/java/com/algorithms/$name/Applications
  touch datastructures/src/main/java/com/algorithms/$name/Applications/Problem1.md
  touch datastructures/src/main/java/com/algorithms/$name/Applications/Problem2.md
  touch datastructures/src/main/java/com/algorithms/$name/$capitalized_name.java
}

# Algorithms
create_algorithm "sorting"
create_algorithm "searching"
create_algorithm "dynamicprogramming"
create_algorithm "backtracking"

echo "Directory structure and files created successfully!"
