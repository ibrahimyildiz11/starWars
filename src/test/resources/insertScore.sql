insert into scores(gebruikerId, filmId, score) VALUES
                                                   ((select id from gebruikers where familienaam = 'test1'),11, 6),
                                                   ((select id from gebruikers where familienaam = 'test2'),11, 7),
                                                   ((select id from gebruikers where familienaam = 'test3'),11, 5),
                                                   ((select id from gebruikers where familienaam = 'test4'),11, 8),
                                                   ((select id from gebruikers where familienaam = 'test5'),11, 4);