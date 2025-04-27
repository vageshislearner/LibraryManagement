import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryManagementSystem extends JFrame {
    private JTextField idField, titleField, authorField, yearField;
    private JTextArea displayArea;
    private BookDAO bookDAO;

    public LibraryManagementSystem() {
        bookDAO = new BookDAO();
        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        idField = new JTextField(5);
        titleField = new JTextField(15);
        authorField = new JTextField(15);
        yearField = new JTextField(5);
        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);

        JButton addButton = new JButton("Add Book");
        JButton viewButton = new JButton("View Books");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Author:"));
        add(authorField);
        add(new JLabel("Year:"));
        add(yearField);

        add(addButton);
        add(viewButton);
        add(updateButton);
        add(deleteButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());

        Book book = new Book(0, title, author, year);
        bookDAO.addBook(book);
        clearFields();
        viewBooks();
    }

    private void viewBooks() {
        List<Book> books = bookDAO.getAllBooks();
        displayArea.setText("");
        for (Book book : books) {
            displayArea.append("ID: " + book.getId() + ", Title: " + book.getTitle()
                    + ", Author: " + book.getAuthor() + ", Year: " + book.getPublishedYear() + "\n");
        }
    }

    private void updateBook() {
        int id = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());

        Book book = new Book(id, title, author, year);
        bookDAO.updateBook(book);
        clearFields();
        viewBooks();
    }

    private void deleteBook() {
        int id = Integer.parseInt(idField.getText());
        bookDAO.deleteBook(id);
        clearFields();
        viewBooks();
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}