# LeBook User Guide

## Introduction
LeBook is a straightforward book management application that runs on the command line. With its clean interface and simple commands, LeBook helps you keep track of your personal book collection, including which books you've borrowed or lent out.

## Quick Start
- Ensure you have Java 17 installed
- Download the latest LeBook.jar from the releases page
- Run the application using: `java -jar LeBook.jar`

## Features

### 1. Adding a Book
Add new books to your library with title and author information.

Format: `add TITLE / AUTHOR`

Example: `add The Great Gatsby / F. Scott Fitzgerald`

```
____________________________________________________________
I've added:
    [ ] The Great Gatsby (by F. Scott Fitzgerald)
Now you have 1 books in the library.
____________________________________________________________
```

### 2. Deleting a Book
Remove books from your library when you no longer want to track them.

Format: `delete BOOK_NUMBER`

Example: `delete 1`

```
____________________________________________________________
Book deleted:
    [ ] The Great Gatsby (by F. Scott Fitzgerald)
Now you have 0 books in the library.
____________________________________________________________
```

### 3. Listing Books
View all the books currently in your library.

Format: `list`

```
____________________________________________________________
Here are the books in your library:
1. [ ] To Kill a Mockingbird (by Harper Lee)
2. [ ] 1984 (by George Orwell)
3. [X] The Hobbit (by J.R.R. Tolkien)
Total books: 3
____________________________________________________________
```

### 4. Borrowing a Book
Mark a book as borrowed.

Format: `borrow BOOK_NUMBER`

Example: `borrow 2`

```
____________________________________________________________
Borrowed: 1984
____________________________________________________________
```

### 5. Returning a Book
Mark a borrowed book as returned.

Format: `return BOOK_NUMBER`

Example: `return 3`

```
____________________________________________________________
Returned: The Hobbit
____________________________________________________________
```

### 6. Exiting the Application
Close the application when you're done.

Format: `exit`

```
____________________________________________________________
Goodbye! See you again.
____________________________________________________________
```

## Book Status Format
In the list view, books are displayed with their status and details:
- `[ ]` indicates an available book
- `[X]` indicates a borrowed book

For example:
- `[ ] Pride and Prejudice (by Jane Austen)` - Available book
- `[X] The Lord of the Rings (by J.R.R. Tolkien)` - Borrowed book

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Add Book | `add TITLE / AUTHOR` | `add The Great Gatsby / F. Scott Fitzgerald` |
| Delete Book | `delete BOOK_NUMBER` | `delete 1` |
| List Books | `list` | `list` |
| Borrow Book | `borrow BOOK_NUMBER` | `borrow 2` |
| Return Book | `return BOOK_NUMBER` | `return 3` |
| Exit | `exit` | `exit` |
