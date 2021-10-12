package com.gracielo.jetpacksubmission3v2.Utils

import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.FilmResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.MovieResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.TVResponse
import java.util.*

object DataDummyFilm {

    fun generateMovies(): ArrayList<MovieEntity> {
        val MoviesList = ArrayList<MovieEntity>()
        MoviesList.add(
            MovieEntity(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                8.2f,
                "Science Fiction, Action, Drama"
            )
        )
        MoviesList.add(
            MovieEntity(
                412656,
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.3f,
                "Science Fiction, Action, Adventure, Thriller"
            )
        )
        MoviesList.add(
            MovieEntity(
                458576,
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                FilmEntity.TYPE_MOVIE,
                "2020",
                7.1f,
                "Fantasy, Action, Adventure"
            )
        )
        MoviesList.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.9f,
                "Fantasy, Action, Adventure, Science Fiction, Thriller"
            )
        )
        return MoviesList
    }

    fun generateRemoteMovies(): ArrayList<MovieResponse> {
        val MoviesList = ArrayList<MovieResponse>()
        MoviesList.add(
            MovieResponse(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                8.2f,
                "Science Fiction, Action, Drama"
            )
        )
        MoviesList.add(
            MovieResponse(
                412656,
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.3f,
                "Science Fiction, Action, Adventure, Thriller"
            )
        )
        MoviesList.add(
            MovieResponse(
                458576,
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                FilmEntity.TYPE_MOVIE,
                "2020",
                7.1f,
                "Fantasy, Action, Adventure"
            )
        )
        MoviesList.add(
            MovieResponse(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                FilmEntity.TYPE_MOVIE,
                "2021",
                7.9f,
                "Fantasy, Action, Adventure, Science Fiction, Thriller"
            )
        )
        return MoviesList
    }

    fun generateTV(): ArrayList<TVEntity> {
        val TVList = ArrayList<TVEntity>()
        TVList.add(
            TVEntity(
                1399,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2021",
                8.4f,
                "Sci-Fi & Fantasy, Drama, Action & Adventure"
            )
        )
        TVList.add(
            TVEntity(
                1402,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2010",
                8.1f,
                "Action & Adventure, Drama, Sci-Fi & Fantasy"
            )
        )
        return TVList
    }

    fun generateRemoteTV(): ArrayList<TVResponse> {
        val TVList = ArrayList<TVResponse>()
        TVList.add(
            TVResponse(
                1399,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2021",
                8.4f,
                "Sci-Fi & Fantasy, Drama, Action & Adventure"
            )
        )
        TVList.add(
            TVResponse(
                1402,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                FilmEntity.TYPE_TV_SHOW,
                "2010",
                8.1f,
                "Action & Adventure, Drama, Sci-Fi & Fantasy"
            )
        )
        return TVList
    }
}