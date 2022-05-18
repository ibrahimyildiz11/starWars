insert into scores(filmId, score, gebruikerId) VALUES
                                                   (11, 6, (select id from gebruikers where familienaam = 'test1')),
                                                   (11, 6, (select id from gebruikers where familienaam = 'test2')),
                                                   (11, 6, (select id from gebruikers where familienaam = 'test3')),
                                                   (11, 6, (select id from gebruikers where familienaam = 'test4')),
                                                   (11, 6, (select id from gebruikers where familienaam = 'test5'));