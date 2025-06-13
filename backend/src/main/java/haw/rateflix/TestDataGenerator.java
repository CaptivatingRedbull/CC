package haw.rateflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import haw.rateflix.repository.ContentRepository;
import haw.rateflix.domain.Content;
import haw.rateflix.domain.Kind;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("testData")
public class TestDataGenerator {

    @Bean
    public CommandLineRunner generateTestData(ContentRepository contentRepository) {
        return args -> {
            List<Content> contentList = new ArrayList<>();

            // Movies
            contentList.add(new Content(Kind.MOVIE, "The Godfather",
                    "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                    1972, 100, 10));
            contentList.add(new Content(Kind.MOVIE, "The Dark Knight",
                    "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
                    2008, 150, 5));
            contentList.add(new Content(Kind.MOVIE, "The Shawshank Redemption",
                    "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                    1994, 200, 3));
            contentList.add(new Content(Kind.MOVIE, "Inception",
                    "A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
                    2010, 180, 7));
            contentList.add(new Content(Kind.MOVIE, "Pulp Fiction",
                    "The lives of two mob hitmen, a boxer, a gangster's wife, and a pair of diner bandits intertwine in tales of violence and redemption.",
                    1994, 120, 4));
            contentList.add(new Content(Kind.MOVIE, "Fight Club",
                    "An insomniac office worker and a soap maker form an underground club with strict rules and revolution on their minds.",
                    1999, 140, 6));
            contentList.add(new Content(Kind.MOVIE, "Forrest Gump",
                    "The presidencies of Kennedy and Johnson, the Vietnam War, and other events unfold from the perspective of an Alabama man.",
                    1994, 160, 4));
            contentList.add(new Content(Kind.MOVIE, "The Matrix",
                    "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                    1999, 170, 8));
            contentList.add(new Content(Kind.MOVIE, "Interstellar",
                    "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                    2014, 200, 6));
            contentList.add(new Content(Kind.MOVIE, "Gladiator",
                    "A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.",
                    2000, 130, 5));
            contentList.add(new Content(Kind.MOVIE, "Titanic",
                    "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious Titanic.",
                    1997, 110, 9));
            contentList.add(new Content(Kind.MOVIE, "The Lord of the Rings: The Fellowship of the Ring",
                    "A meek Hobbit and eight companions set out on a journey to destroy a powerful ring.", 2001, 180,
                    7));
            contentList.add(new Content(Kind.MOVIE, "The Silence of the Lambs",
                    "A young FBI cadet must confide in an incarcerated and manipulative killer to catch another serial killer.",
                    1991, 95, 3));
            contentList.add(new Content(Kind.MOVIE, "The Green Mile",
                    "The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.",
                    1999, 125, 4));
            contentList.add(new Content(Kind.MOVIE, "Se7en",
                    "Two detectives hunt a serial killer who uses the seven deadly sins as his motives.", 1995, 145,
                    6));
            contentList.add(new Content(Kind.MOVIE, "Whiplash",
                    "A promising young drummer enrolls at a cut-throat music conservatory where he clashes with an abusive instructor.",
                    2014, 90, 5));
            contentList.add(new Content(Kind.MOVIE, "The Prestige",
                    "Two stage magicians engage in a competitive rivalry that leads to tragic results.", 2006, 110, 3));
            contentList.add(new Content(Kind.MOVIE, "Django Unchained",
                    "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.",
                    2012, 150, 4));
            contentList.add(new Content(Kind.MOVIE, "The Departed",
                    "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in Boston.",
                    2006, 140, 4));
            contentList.add(new Content(Kind.MOVIE, "Joker",
                    "In Gotham's fractured society, a mentally troubled man embarks on a downward spiral of revolution and crime.",
                    2019, 170, 9));
            contentList.add(new Content(Kind.MOVIE, "The Social Network",
                    "The story of the founders of the social-networking website Facebook.", 2010, 85, 2));
            contentList.add(new Content(Kind.MOVIE, "Mad Max: Fury Road",
                    "In a post-apocalyptic wasteland, Max helps a rebel warrior and a group of female prisoners flee from a tyrant.",
                    2015, 95, 4));
            contentList.add(new Content(Kind.MOVIE, "La La Land",
                    "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations.",
                    2016, 105, 4));
            contentList.add(new Content(Kind.MOVIE, "Parasite",
                    "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.",
                    2019, 115, 6));
            contentList.add(new Content(Kind.MOVIE, "Everything Everywhere All At Once",
                    "An aging Chinese immigrant is swept up in an insane adventure where she alone can save the world by exploring other universes.",
                    2022, 98, 7));
            // Series
            contentList.add(new Content(Kind.SERIES, "Breaking Bad",
                    "A high school chemistry teacher turned methamphetamine producer partners with a former student.",
                    2008, 220, 5));
            contentList.add(new Content(Kind.SERIES, "Game of Thrones",
                    "Nine noble families wage war against each other to gain control over the mythical land of Westeros.",
                    2011, 240, 8));
            contentList.add(new Content(Kind.SERIES, "Stranger Things",
                    "A group of young friends witness supernatural forces and secret experiments in their town.", 2016,
                    200, 6));
            contentList.add(new Content(Kind.SERIES, "The Office",
                    "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.",
                    2005, 180, 4));
            contentList.add(new Content(Kind.SERIES, "The Crown",
                    "Follows the political rivalries and romance of Queen Elizabeth II's reign.", 2016, 160, 3));
            contentList.add(new Content(Kind.SERIES, "The Mandalorian",
                    "A lone gunfighter makes his way through the outer reaches of the galaxy, far from the authority of the New Republic.",
                    2019, 140, 6));
            contentList.add(new Content(Kind.SERIES, "Friends",
                    "Follows the personal and professional lives of six twenty to thirty-something friends living in Manhattan.",
                    1994, 300, 7));
            contentList.add(new Content(Kind.SERIES, "The Simpsons",
                    "The satiric adventures of a working-class family in the misfit city of Springfield.", 1989, 280,
                    10));
            contentList.add(new Content(Kind.SERIES, "Better Call Saul",
                    "The trials and tribulations of criminal lawyer Jimmy McGill in the time before he establishes his strip-mall law office.",
                    2015, 190, 5));
            contentList.add(new Content(Kind.SERIES, "The Boys",
                    "A group of vigilantes set out to take down corrupt superheroes who abuse their powers.", 2019, 120,
                    4));
            contentList.add(new Content(Kind.SERIES, "Peaky Blinders",
                    "A gangster family epic set in 1900s England, centered on a gang who sew razor blades in the peaks of their caps.",
                    2013, 170, 5));
            contentList.add(new Content(Kind.SERIES, "The Witcher",
                    "A solitary monster hunter struggles to find his place in a world where people often prove more wicked than beasts.",
                    2019, 130, 3));
            contentList.add(new Content(Kind.SERIES, "Westworld",
                    "A dark odyssey about the dawn of artificial consciousness and the evolution of sin.", 2016, 150,
                    3));
            contentList.add(new Content(Kind.SERIES, "Black Mirror",
                    "An anthology series exploring a twisted, high-tech world where humanity's greatest innovations and darkest instincts collide.",
                    2011, 160, 6));
            contentList.add(new Content(Kind.SERIES, "Sherlock",
                    "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                    2010, 140, 4));
            contentList.add(new Content(Kind.SERIES, "House of the Dragon",
                    "The story of House Targaryen, set 200 years before the events of Game of Thrones.", 2022, 130, 3));
            contentList.add(new Content(Kind.SERIES, "The Last of Us",
                    "After a global pandemic, a smuggler must escort a young girl across a devastated U.S.", 2023, 150,
                    6));
            contentList.add(new Content(Kind.SERIES, "Ozark",
                    "A financial advisor drags his family from Chicago to the Missouri Ozarks, where he must launder money for a drug cartel.",
                    2017, 110, 5));
            contentList.add(new Content(Kind.SERIES, "The Umbrella Academy",
                    "A dysfunctional family of adopted superhero siblings reunite to solve the mystery of their father's death.",
                    2019, 100, 3));
            contentList.add(new Content(Kind.SERIES, "Money Heist",
                    "An unusual group of robbers attempt to carry out the most perfect robbery in Spanish history.",
                    2017, 160, 4));
            contentList.add(new Content(Kind.SERIES, "Dark",
                    "A family saga with a supernatural twist, set in a German town where the disappearance of two young children exposes secrets.",
                    2017, 90, 4));
            contentList.add(new Content(Kind.SERIES, "Succession",
                    "The Roy family controls the biggest media and entertainment company in the world. But their world changes when their father steps down.",
                    2018, 140, 3));
            contentList.add(new Content(Kind.SERIES, "Fargo",
                    "Various chronicles of deception, intrigue and murder in and around frozen Minnesota. Yet all of these tales mysteriously lead back one way or another to Fargo.",
                    2014, 100, 3));
            contentList.add(new Content(Kind.SERIES, "Chernobyl",
                    "A dramatization of the true story of one of the worst man-made catastrophes in history: the catastrophic nuclear accident at Chernobyl.",
                    2019, 90, 5));
            contentList.add(new Content(Kind.SERIES, "True Detective",
                    "Anthology series in which police investigations unearth personal and professional secrets of those involved.",
                    2014, 110, 4));

            
            contentRepository.saveAll(contentList);
        };
    }
}