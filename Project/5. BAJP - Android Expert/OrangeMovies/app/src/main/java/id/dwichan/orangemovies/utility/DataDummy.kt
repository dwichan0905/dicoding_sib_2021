package id.dwichan.orangemovies.utility

import id.dwichan.orangemovies.R
import id.dwichan.orangemovies.data.Crew
import id.dwichan.orangemovies.data.Movie
import id.dwichan.orangemovies.data.TvShow

object DataDummy {

    fun generateMovies(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()

        movies.add(Movie(
            movieId = "m01",
            title = "A Star is Born",
            releaseDate = "05/10/2018",
            crews = arrayListOf(
                Crew(name = "Bradley Cooper", job = "Director, Screenplay"),
                Crew(name = "Will Fetters", job = "Screenplay"),
                Crew(name = "Eric Roth", job = "Screenplay"),
                Crew(name = "William A. Wellman", job = "Story")
            ),
            category = "Drama, Percintaan, Musik",
            synopsis = """
                Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.
            """.trimIndent(),
            certification = "R",
            userScore = 75,
            duration = "2h 16m",
            poster = R.drawable.poster_a_start_is_born,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m02",
            title = "Alita: Battle Angel",
            releaseDate = "14/02/2019",
            crews = arrayListOf(
                Crew(name = "Robert Rodriguez", job = "Director"),
                Crew(name = "Laeta Kalogridis", job = "Screenplay"),
                Crew(name = "James Cameron", job = "Screenplay")
            ),
            category = "Aksi, Cerita Fiksi, Petualangan",
            synopsis = """
                Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 71,
            duration = "2h 2m",
            poster = R.drawable.poster_alita,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m03",
            title = "Aquaman",
            releaseDate = "21/12/2018",
            crews = arrayListOf(
                Crew(name = "James Wan", job = "Director, Story"),
                Crew(name = "Will Beall", job = "Screenplay, Story"),
                Crew(name = "Paul Norris", job = "Characters"),
                Crew(name = "Mort Weisinger", job = "Characters"),
                Crew(name = "David Leslie Johnson-McGoldrick", job = "Screenplay"),
                Crew(name = "Geoff Johns", job = "Story")
            ),
            category = "Aksi, Petualangan, Fantasi",
            synopsis = """
                Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 69,
            duration = "2h 24m",
            poster = R.drawable.poster_aquaman,
            highlight = true
        ))
        movies.add(Movie(
            movieId = "m04",
            title = "Bohemian Rhapsody",
            releaseDate = "02/11/2018",
            crews = arrayListOf(
                Crew(name = "Anthony McCarten", job = "Screenplay, Story"),
                Crew(name = "Bryan Singer", job = "Director"),
                Crew(name = "Peter Morgan", job = "Story")
            ),
            category = "Aksi, Petualangan, Fantasi",
            synopsis = """
                Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 80,
            duration = "2h 15m",
            poster = R.drawable.poster_bohemian,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m05",
            title = "Cold Pursuit",
            releaseDate = "08/02/2019",
            crews = arrayListOf(
                Crew(name = "Hans Petter Moland", job = "Director"),
                Crew(name = "Frank Baldwin", job = "Screenplay")
            ),
            category = "Aksi, Kejahatan, Cerita Seru",
            synopsis = """
                The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.
            """.trimIndent(),
            certification = "R",
            userScore = 56,
            duration = "1h 59m",
            poster = R.drawable.poster_cold_persuit,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m06",
            title = "Creed",
            releaseDate = "25/11/2015",
            crews = arrayListOf(
                Crew(name = "Ryan Coogler", job = "Screenplay, Director, Story"),
                Crew(name = "Sylvester Stallone", job = "Characters"),
                Crew(name = "Aaron Covington", job = "Screenplay")
            ),
            category = "Drama",
            synopsis = """
                The former World Heavyweight Champion Rocky Balboa serves as a trainer and mentor to Adonis Johnson, the son of his late friend and former rival Apollo Creed.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 74,
            duration = "2h 13m",
            poster = R.drawable.poster_creed,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m07",
            title = "Fantastic Beasts: The Crimes of Grindelwald",
            releaseDate = "16/11/2018",
            crews = arrayListOf(
                Crew(name = "David Yates", job = "Director"),
                Crew(name = "J.K. Rowling", job = "Screenplay")
            ),
            category = "Petualangan, Fantasi, Drama",
            synopsis = """
                Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 69,
            duration = "2h",
            poster = R.drawable.poster_crimes,
            highlight = false
        ))
        movies.add(Movie(
            movieId = "m08",
            title = "Glass",
            releaseDate = "18/01/2019",
            crews = arrayListOf(
                Crew(name = "Anthony McCarten", job = "Director, Screenplay")
            ),
            category = "Cerita Seru, Drama, Cerita Fiksi",
            synopsis = """
                In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 66,
            duration = "2h 9m",
            poster = R.drawable.poster_glass,
            highlight = true
        ))
        movies.add(Movie(
            movieId = "m09",
            title = "How to Train Your Dragon",
            releaseDate = "26/03/2010",
            crews = arrayListOf(
                Crew(name = "Dean DeBlois", job = "Director, Screenplay"),
                Crew(name = "Chris Sanders", job = "Director, Screenplay"),
                Crew(name = "Cressida Cowell", job = "Novel"),
                Crew(name = "Will Davies", job = "Screenplay")
            ),
            category = "Fantasi, Petualangan, Animasi, Keluarga",
            synopsis = """
                As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father
            """.trimIndent(),
            certification = "PG",
            userScore = 78,
            duration = "1h 40m",
            poster = R.drawable.poster_how_to_train,
            highlight = true
        ))
        movies.add(Movie(
            movieId = "m10",
            title = "Avengers: Infinity War",
            releaseDate = "27/04/2018",
            crews = arrayListOf(
                Crew(name = "Joe Russo", job = "Director"),
                Crew(name = "Anthony Russo", job = "Director"),
                Crew(name = "Stephen McFeely", job = "Screenplay"),
                Crew(name = "Christopher Markus", job = "Screenplay")
            ),
            category = "Petualangan, Aksi, Cerita Fiksi",
            synopsis = """
                Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.
            """.trimIndent(),
            certification = "PG-13",
            userScore = 83,
            duration = "2h 29m",
            poster = R.drawable.poster_infinity_war,
            highlight = true
        ))
        movies.add(Movie(
            movieId = "m11",
            title = "Mary Queen of Scots",
            releaseDate = "21/12/2018",
            crews = arrayListOf(
                Crew(name = "Joe Russo", job = "Director"),
                Crew(name = "Anthony Russo", job = "Director"),
                Crew(name = "Stephen McFeely", job = "Screenplay"),
                Crew(name = "Christopher Markus", job = "Screenplay")
            ),
            category = "Drama, Sejarah",
            synopsis = """
                In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.
            """.trimIndent(),
            certification = "R",
            userScore = 66,
            duration = "2h 4m",
            poster = R.drawable.poster_marry_queen,
            highlight = false
        ))

        return movies
    }

    fun getMovieData(movieId: String): Movie? {
        lateinit var movie: Movie
        for (movies in generateMovies()) {
            if (movies.movieId == movieId) {
                movie = movies
            }
        }
        return movie
    }

    fun getMovieCrews(movieId: String): ArrayList<Crew> {
        val crews = ArrayList<Crew>()

        for (movie in generateMovies()) {
            if (movie.movieId == movieId) {
                crews.addAll(movie.crews)
            }
        }

        return crews
    }

    fun getHighlightMovies(): ArrayList<Movie> {
        val highlights = ArrayList<Movie>()

        for (movie in generateMovies()) {
            if (movie.highlight) {
                highlights.add(movie)
            }
        }

        return highlights
    }

    fun generateTvShows(): ArrayList<TvShow> {
        val tvShows = ArrayList<TvShow>()

        tvShows.add(TvShow(
            tvShowId = "tv01",
            title = "The Arrow",
            year = 2012,
            synopsis = """
                Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow
            """.trimIndent(),
            certification = "TV-14",
            userScore = 65,
            crews = arrayListOf(
                Crew(name = "Greg Berlati", job = "Creator"),
                Crew(name = "Marc Guggenheim", job = "Creator"),
                Crew(name = "Anrew Kreisberg", job = "Creator")
            ),
            category = "Kejahatan, Drama, Misteri, Aksi & Petualangan",
            poster = R.drawable.poster_arrow,
            duration = "42m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv02",
            title = "Doom Patrol",
            year = 2019,
            synopsis = """
                The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.
            """.trimIndent(),
            certification = "TV-MA",
            userScore = 75,
            crews = arrayListOf(
                Crew(name = "Jeremy Carver", job = "Creator")
            ),
            category = "Sci-fi & Fantasy, Aksi & Petualangan",
            poster = R.drawable.poster_doom_patrol,
            duration = "49m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv03",
            title = "Dragon Ball (ドラゴンボール)",
            year = 1986,
            synopsis = """
                Dahulu kala di pegunungan, seorang master pertempuran yang dikenal sebagai Gohan menemukan seorang bocah aneh yang ia beri nama Goku. Gohan membesarkannya dan melatih Goku dalam seni bela diri sampai dia mati. Bocah muda dan sangat kuat itu sendirian, tetapi mudah dikelola. Kemudian suatu hari, Goku bertemu dengan seorang gadis remaja bernama Bulma, yang pencariannya untuk bola naga membawanya ke rumah Goku. Bersama-sama, mereka berangkat untuk menemukan ketujuh bola naga dalam sebuah petualangan yang akan mengubah hidup Goku selamanya. Lihat bagaimana Goku bertemu teman-teman seumur hidupnya Bulma, Yamcha, Krillin, Master Roshi dan banyak lagi.
            """.trimIndent(),
            certification = "PG",
            userScore = 80,
            crews = arrayListOf(
                Crew(name = "Akira Toriyama", job = "Creator")
            ),
            category = "Komedi, Sci-fi & Fantasy, Animasi, Aksi & Petualangan",
            poster = R.drawable.poster_dragon_ball,
            duration = "25m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv04",
            title = "Fairytail (フェアリーテイル)",
            year = 2009,
            synopsis = """
                Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.
            """.trimIndent(),
            certification = "TV-14",
            userScore = 75,
            crews = arrayListOf(
                Crew(name = "Shinji Ishihira", job = "Director"),
                Crew(name = "Atsushi Iwasaki", job = "Director of Photography")
            ),
            category = "Aksi & Petualangan, Animasi, Komedi, Sci-fi & Fantasy",
            poster = R.drawable.poster_fairytail,
            duration = "25m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv05",
            title = "Family Guy",
            year = 1999,
            synopsis = """
                Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ). Anggota terakhir keluarga itu adalah Brian - anjing yang bisa bicara dan lebih dari sekadar hewan peliharaan, ia menjaga Stewie, sementara menghirup Martinis dan memilah-milah masalah hidupnya sendiri.
            """.trimIndent(),
            certification = "TV-14",
            userScore = 68,
            crews = arrayListOf(
                Crew(name = "Seth MacFarlane", job = "Creator")
            ),
            category = "Animasi, Komedi",
            poster = R.drawable.poster_family_guy,
            duration = "22m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv06",
            title = "The Flash",
            year = 2014,
            synopsis = """
                Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya "manusia meta" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.
            """.trimIndent(),
            certification = "TV-PG",
            userScore = 75,
            crews = arrayListOf(
                Crew(name = "Greg Berlanti", job = "Creator")
            ),
            category = "Drama, Sci-fi & Fantasy",
            poster = R.drawable.poster_flash,
            duration = "44m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv07",
            title = "The Walking Dead",
            year = 2010,
            synopsis = """
                Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.
            """.trimIndent(),
            certification = "TV-MA",
            userScore = 79,
            crews = arrayListOf(
                Crew(name = "Frank Darabont", job = "Creator")
            ),
            category = "Aksi & Petualangan, Drama, Sci-fi & Fantasy",
            poster = R.drawable.poster_the_walking_dead,
            duration = "42m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv08",
            title = "Gotham",
            year = 2014,
            synopsis = """
                Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka - persona yang lebih besar dari kehidupan yang akan menjadi Catwoman, The Penguin, The Riddler, Two-Face dan The Joker?
            """.trimIndent(),
            certification = "TV-14",
            userScore = 74,
            crews = arrayListOf(
                Crew(name = "Bruno Heller", job = "Creator")
            ),
            category = "Drama, Fantasi, Kejahatan",
            poster = R.drawable.poster_gotham,
            duration = "43m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv09",
            title = "Grey's Anatomy",
            year = 2005,
            synopsis = """
                Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.
            """.trimIndent(),
            certification = "TV-14",
            userScore = 80,
            crews = arrayListOf(
                Crew(name = "Shonda Rhimes", job = "Creator")
            ),
            category = "Drama",
            poster = R.drawable.poster_grey_anatomy,
            duration = "43m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv10",
            title = "Naruto Shippuden",
            year = 2007,
            synopsis = """
                Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.
            """.trimIndent(),
            certification = "TV-14",
            userScore = 87,
            crews = arrayListOf(
                Crew(name = "Hayato Date", job = "Director"),
                Crew(name = "Yasuyuki Suzuki", job = "Writer"),
                Crew(name = "Shin Yoshida", job = "Writer"),
                Crew(name = "Katsuhiko Chiba", job = "Writer")
            ),
            category = "Animasi, Komedi, Drama",
            poster = R.drawable.poster_naruto_shipudden,
            duration = "25m"
        ))
        tvShows.add(TvShow(
            tvShowId = "tv11",
            title = "The Simpsons",
            year = 1989,
            synopsis = """
                Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.
            """.trimIndent(),
            certification = "TV-PG",
            userScore = 77,
            crews = arrayListOf(
                Crew(name = "Matt Groening", job = "Creator")
            ),
            category = "Animasi, Komedi, Keluarga, Drama",
            poster = R.drawable.poster_the_simpson,
            duration = "22m"
        ))

        return tvShows
    }

    fun getTvShowData(tvId: String): TvShow? {
        lateinit var tvShow: TvShow
        for (tv in generateTvShows()) {
            if (tv.tvShowId == tvId) {
                tvShow = tv
            }
        }
        return tvShow
    }

    fun getTvShowCrews(tvId: String): ArrayList<Crew> {
        val crews = ArrayList<Crew>()

        for (tvShow in generateTvShows()) {
            if (tvShow.tvShowId == tvId) {
                crews.addAll(tvShow.crews)
            }
        }

        return crews
    }
}