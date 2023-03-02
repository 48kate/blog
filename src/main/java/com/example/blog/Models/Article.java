package com.example.blog.Models;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

// отвечает за хранение всех статей
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String full_text;
    private String category;
    private String img;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", author='" + author + "'" +
                ", full_text='" + full_text + "'" +
                ", category='" + full_text + "'" +
                "}";
    }
}
