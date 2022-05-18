"use strict";
const filmsUrl = "http://localhost:8080/films";
document.getElementById("zoeken").onclick = zoekFilm;

async function zoekFilm() {
    verbergFouten();
    const filmResponse = await fetch(`${filmsUrl}/${document.getElementById("nummer").value}`);
    if (filmResponse.ok) {
        const film = await filmResponse.json();
        toonDetailVan(film);
        zoekPeople(film);
    } else {
        if (filmResponse.status === 404) {
            document.getElementById("filmNietGevondenFout").hidden = false;
        } else {
            document.getElementById("technischeFout").hidden = false;
        }
    }
}

function toonDetailVan(film) {
    document.getElementById("title").innerText = film.title;
    document.getElementById("director").innerText = film.director;
    document.getElementById("releaseDate").innerText = film.releaseDate;
}

async function zoekPeople(film) {
    const ul = document.getElementById("people");
    verwijderAllChildElementenIn(ul);
    const peopleResponse = await fetch(film._links.people.href);
    if (peopleResponse.ok) {
        const people = await peopleResponse.json();
        for (const persoon of people._embedded.peopleList) {
            const li = document.createElement("li");
            li.innerText = `${persoon.name}`;
            ul.appendChild(li);
        }
    } else {
        document.getElementById("technischeFout").hidden = false;
    }
}

function verwijderAllChildElementenIn(element) {
    while (element.lastChild !== null) {
        element.lastChild.remove();
    }
}

function verbergFouten() {
    for (const div of document.querySelectorAll(".fout")) {
        div.hidden = true;
    }
}