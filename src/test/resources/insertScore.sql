insert into scores(gebruikerId, filmId, score) VALUES
                                                   ((select id from gebruikers where familienaam = 'test2' ), 12, 8),
                                                   ((select id from gebruikers where familienaam = 'test3' ), 12, 7),
                                                   ((select id from gebruikers where familienaam = 'test4' ), 12, 5),
                                                   ((select id from gebruikers where familienaam = 'test5' ), 12, 4);