package com.example.composeapp.data

import com.example.composeapp.models.Cinema
import com.example.composeapp.models.City
import com.example.composeapp.models.Movie

object MovieData {
    val dummyMovies = listOf(
        Movie(
            1,
            "The Shawshank Redemption",
            "The Shawshank Redemption (1994) follows Andy Dufresne (Tim Robbins), a banker sentenced to life at Shawshank State Penitentiary for murdering his wife and her lover, despite his innocence. Over two decades, he befriends contraband smuggler Ellis \"Red\" Redding (Morgan Freeman), endures brutal prison life, and helps the warden with money laundering. Andy maintains hope, eventually escaping through a tunnel he dug, exposing the warden's corruption, and reuniting with Red in Mexico.",
            9.3,
            "https://rricoid-assets.obs.ap-southeast-4.myhuaweicloud.com/berita/Kendari/o/1734760657452-the_shawshank_redemption_posterlarge_0-675188670/89chdayli2qlrks.jpeg",
            "Drama",
            "1994",
            "1h 22m",
            "Frank Darabont",
            "Stephen King, Frank Darabont",
            listOf("Tim Robbins", "Morgan Freeman", "Bob Gunton")
        ),
        Movie(
            2,
            "The Godfather",
            "The Godfather (1972) is a Shakespearean-style crime drama about the reluctant rise of Michael Corleone, who transforms from a war hero into the ruthless new patriarch of his family's organized crime dynasty. Directed by Francis Ford Coppola and based on Mario Puzo’s novel, it explores themes of loyalty, power, and the corruption of the American Dream.",
            9.2,
            "https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_.jpg",
            "Drama",
            "1972",
            "2h 55m",
            "Francis Ford Coppola",
            "Mario Puzo, Francis Ford Coppola",
            listOf ("Marlon Brando", "Al Pacino", "James Caan")
        ),
        Movie(
            3,
            "The Dark Knight",
            "The Dark Knight (2008), Batman (Christian Bale) faces his greatest psychological and physical test when the anarchistic Joker (Heath Ledger) plunges Gotham City into chaos. Along with Lt. Jim Gordon and District Attorney Harvey Dent, Batman attempts to dismantle organized crime, but the Joker aims to destroy Gotham's order and force Batman to break his one rule. The film explores themes of morality, chaos, and sacrifice, resulting in Dent's tragic transformation into Two-Face.",
            9.1,
            "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg",
            "Action",
            "2008",
            "2h 32m",
            "Christopher Nolan",
            "Jonathan Nolan, Christopher Nolan",
            listOf ("Christian Bale", "Heath Ledger", "Aaron Eckhart")
        ),
        Movie(
            4,
            "Inception",
            "Inception (2010) is a sci-fi action film about Dom Cobb (Leonardo DiCaprio), a thief skilled in \"extraction\"—stealing secrets from the subconscious during shared dreaming. To return home to his children, Cobb is tasked with the opposite: \"inception,\" planting an idea rather than stealing one, requiring his team to navigate multiple, dangerous dream levels.",
            8.8,
            "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg",
            "Sci-Fi",
            "2010",
            "2h 28m",
            "Christopher Nolan",
            "Christopher Nolan",
            listOf ("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page")
        ),
        Movie(
            5,
            "Pulp Fiction",
            "Pulp Fiction (1994) is a non-linear crime film directed by Quentin Tarantino that intertwines several storylines in Los Angeles, focusing on hitmen, a boxer, a gangster's wife, and armed robbers. The narrative explores themes of violence, redemption, and irony through sharp dialogue, featuring iconic stories involving Vincent Vega (John Travolta) and Jules Winnfield (Samuel L. Jackson).",
            8.8,
            "https://m.media-amazon.com/images/I/61duEL68AwL._AC_UF894,1000_QL80_.jpg",
            "Action",
            "1994",
            "2h 34m",
            "Quentin Tarantino",
            "Quentin Tarantino, Roger Avary",
            listOf ("John Travolta", "Uma Thurman", "Samuel L. Jackson")
        )
    )
}

object CinemaData {
    val dummyCities = listOf(
        City(1, "Semarang"),
        City(2, "Jakarta"),
        City(3, "Surabaya"),
        City(4, "Bandung")
    )

    val dummyCinemas = listOf(
        Cinema(1, "Semarang XXI", "DP Mall, Jl. Pemuda No.150", 1),
        Cinema(2, "Cinepolis Java Mall", "Java Supermall, Jl. MT. Haryono", 1),
        Cinema(3, "Grand Indonesia CGV", "Grand Indonesia East Mall", 2),
        Cinema(4, "Plaza Senayan XXI", "Plaza Senayan Lt. 5", 2),
        Cinema(5, "Tunjungan Plaza 5 IMAX", "Jl. Jenderal Basuki Rachmat", 3),
        Cinema(6, "Paris Van Java CGV", "Jl. Sukajadi No.131", 4)
    )
}

