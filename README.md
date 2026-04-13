# WordNet Semantic Search Engine

A browser-based search tool that returns semantically related words (hyponyms) from the WordNet dataset, with optional frequency-based filtering using historical NGram data.

---

## Overview

This tool allows a user to type in a word or list of words and receive all words that are more specific versions of the input — for example, typing "dog" returns "poodle", "labrador", "dachshund", etc. Results are sorted alphabetically and can be filtered by historical frequency and a desired count k.

---

## Features

- Single and multi-word hyponym search
- Alphabetically sorted results with no duplicates
- Frequency-based filtering using NGram historical data (k != 0)
- Live browser interface connected to a Java backend server

---

## Tech Stack

- Java
- HTML / JavaScript
- Princeton `algs4` library
- Google Truth testing framework

---

## Architecture

| Class | Responsibility |
|---|---|
| `DGraph` | Directed graph storing synset relationships via adjacency list |
| `ReadWordNet` | Parses synset and hyponym files into HashMaps at startup |
| `TraverseWordNet` | BFS traversal to find hyponyms of one or more words |
| `HyponymsHandler` | Handles browser queries and returns results as a string |
| `AutograderBuddy` | Wires handlers for autograder testing |

---

## How It Works

1. On startup, synset and hyponym files are parsed once into memory
2. Each synset ID is registered as a node in the directed graph
3. When a query arrives, BFS traverses the graph from the query word's synset IDs
4. All reachable words are collected into a TreeSet (sorted, no duplicates)
5. For multi-word queries, the intersection of each word's hyponym set is returned
6. If k > 0, results are filtered by historical frequency using NGramMap

---

## Running the Project

1. Ensure data files are placed in the `data/` directory
2. Run `Main.java` in IntelliJ
3. Open `static/ngordnet.html` in your browser
4. Enter a word and click **Hyponyms**

---

## Testing

Unit tests are located in the `tests/` directory. Tests cover:

- Single word hyponym lookup
- Multi-word intersection
- Leaf node edge cases
- Words not found in the dataset

Run tests directly in IntelliJ using the green play button next to each test method.

---

## Data Files

| File | Description |
|---|---|
| `synsets_size82191.txt` | Full WordNet synset dataset |
| `hyponyms_size82191.txt` | Full WordNet hyponym relationships |
| `synsets_size16.txt` | Small dataset for local testing |
| `hyponyms_size16.txt` | Small dataset for local testing |
