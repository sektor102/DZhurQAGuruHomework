package guru.qa.dZhurHomeWork.less8.data;

    public enum Language {
        en("MyShows - Best Movies and TV Shows from Around the World"),
        ru("MyShows - лучшие фильмы и сериалы мира"),
        ua("MyShows - найкращі фільми та серіали світу");


        public final String description;

        Language(String description) {
            this.description = description;
        }
    }
