package haw.rateflix.domain;

import org.hibernate.annotations.Formula;

import jakarta.persistence.*;

/**
 * Represents a content item in the Rateflix application.
 * Each content has a unique ID, a kind (e.g., movie, series), a title,
 * a description, a release year, and vote counts (upvotes and downvotes).
 * The content is stored in a database table named "contents".
 */
@Entity
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kind kind;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int year;

    private int upVote;

    private int downVote;

    @Formula("up_vote - down_vote")
    private int score;

    // Constructors
    public Content() {
    }

    public Content(Kind kind, String title, String description, int year, int upVote, int downVote) {
        this.kind = kind;
        this.title = title;
        this.description = description;
        this.year = year;
        this.upVote = upVote;
        this.downVote = downVote;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getUpVote() {
        return upVote;
    }

    public void setUpVote(int upVote) {
        this.upVote = upVote;
    }

    public int getDownVote() {
        return downVote;
    }

    public void setDownVote(int downVote) {
        this.downVote = downVote;
    }

    public int getScore() {
        return score;
    }
}
