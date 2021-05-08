package com.weslie10.showmo.utils

import com.weslie10.showmo.data.source.local.entities.MovieEntity
import com.weslie10.showmo.data.source.local.entities.TvShowEntity
import com.weslie10.showmo.data.source.remote.response.MovieResponse
import com.weslie10.showmo.data.source.remote.response.ResultsMovie
import com.weslie10.showmo.data.source.remote.response.ResultsTvShow
import com.weslie10.showmo.data.source.remote.response.TvShowResponse

object DataDummy {
    val moviePopular = MovieResponse(
        arrayListOf(
            ResultsMovie(
                399566,
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24"
            ),
            ResultsMovie(
                460465,
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "/6K0RCDfP9ExbTcYgryaQHTGmET7.jpg",
                "2021-04-07",
            ),
            ResultsMovie(
                615678,
                "Thunder Force",
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "/279yOM4OQREL36B3SECnRxoB4MZ.jpg",
                "2021-04-09",
            ),
            ResultsMovie(
                791373,
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "2021-03-18",
            ),
            ResultsMovie(
                634528,
                "The Marksman",
                "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
                "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                "2021-01-15",
            ),
            ResultsMovie(
                615457,
                "Nobody",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-18",
            ),
            ResultsMovie(
                412656,
                "Chaos Walking",
                "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                "2021-02-24",
            ),
            ResultsMovie(
                632357,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "2021-03-31",
            ),
            ResultsMovie(
                527774,
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
            ),
            ResultsMovie(
                664767,
                "Mortal Kombat Legends: Scorpion's Revenge",
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                "/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "2020-04-12",
            ),
            ResultsMovie(
                458576,
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",
            ),
            ResultsMovie(
                587807,
                "Tom & Jerry",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "2021-02-11",
            ),
            ResultsMovie(
                793723,
                "Sentinelle",
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "2021-03-05",
            ),
            ResultsMovie(
                635302,
                "Demon Slayer – Kimetsu no Yaiba – The Movie: Mugen Train",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "2020-10-16",
            ),
            ResultsMovie(
                464052,
                "Wonder Woman 1984",
                "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
            ),
            ResultsMovie(
                544401,
                "Cherry",
                "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
                "/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                "2021-02-26",
            ),
            ResultsMovie(
                606523,
                "Night in Paradise",
                "An assassin named Tae-goo is offered a chance to switch sides with his rival Bukseong gang, headed by Chairman Doh. Tae-goo rejects the offer that results in the murder of his sister and niece. In revenge, Tae-goo brutally kills Chairman Doh and his men and flees to Jeju Island where he meets Jae-yeon, a terminally ill woman. Though, the henchman of the Bukseong gang, Executive Ma is mercilessly hunting Tae-goo to take revenge.",
                "/dYCWUAidqgakGETwZkfGxU7CWhL.jpg",
                "2020-09-03",
            ),
            ResultsMovie(
                775996,
                "Outside the Wire",
                "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.",
                "/6XYLiMxHAaCsoyrVo38LBWMw2p8.jpg",
                "2021-01-15",
            ),
            ResultsMovie(
                581389,
                "Space Sweepers",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                "/p9YDHJKvUoi7v2SCd3vLGPae1Xp.jpg",
                "2021-02-05",
            ),
            ResultsMovie(
                644083,
                "Twist",
                "A Dicken’s classic brought thrillingly up to date in the teeming heartland of modern London, where a group of street smart young hustlers plan the heist of the century for the ultimate payday.",
                "/29dCusd9PwHrbDqzxNG35WcpZpS.jpg",
                "2021-01-22",
            )
        )
    )

    val movie = MovieEntity(
        399566,
        "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
        "Godzilla vs. Kong",
        "2021-03-24",
        113,
        8.2,
        "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
        "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
        "One Will Fall",
        "Godzilla vs. Kong",
        spokenLanguages = "English",
        genres = "Action,Science Fiction",
        "https://www.godzillavskong.net/",
        "Released",
        390215000,
        200000000,
    )

    val tvShowPopular = TvShowResponse(
        arrayListOf(
            ResultsTvShow(
                88396,
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "2021-03-19",
            ),
            ResultsTvShow(
                71712,
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25",
            ),
            ResultsTvShow(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07",
            ),
            ResultsTvShow(
                95557,
                "Invincible",
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "2021-03-26",
            ),
            ResultsTvShow(
                79008,
                "Luis Miguel: The Series",
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "2018-04-22",
            ),
            ResultsTvShow(
                1416,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "2005-03-27",
            ),
            ResultsTvShow(
                69050,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "2017-01-26",
            ),
            ResultsTvShow(
                63174,
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
            ),
            ResultsTvShow(
                85271,
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "2021-01-15",
            ),
            ResultsTvShow(
                1402,
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "2010-10-31",
            ),
            ResultsTvShow(
                120168,
                "Who Killed Sara?",
                "Hell-bent on exacting revenge and proving he was framed for his sister's murder, Álex sets out to unearth much more than the crime's real culprit.",
                "/o7uk5ChRt3quPIv8PcvPfzyXdMw.jpg",
                "2021-03-24",
            ),
            ResultsTvShow(
                95057,
                "Superman & Lois",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                "/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                "2021-02-23",
            ),
            ResultsTvShow(
                1399,
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "2011-04-17",
            ),
            ResultsTvShow(
                62286,
                "Fear the Walking Dead",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "/4UjiPdFKJGJYdxwRs2Rzg7EmWqr.jpg",
                "2015-08-23",
            ),
            ResultsTvShow(
                79460,
                "Legacies",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                "2018-10-25",
            ),
            ResultsTvShow(
                77169,
                "Cobra Kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "2018-05-02",
            ),
            ResultsTvShow(
                44217,
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                "2013-03-03",
            ),
            ResultsTvShow(
                114695,
                "Marvel Studios: Legends",
                "Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                "/EpDuYIK81YtCUT3gH2JDpyj8Qk.jpg",
                "2021-01-08",
            ),
            ResultsTvShow(
                18165,
                "The Vampire Diaries",
                "The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.",
                "/kLEha9zVVv8acGFKTX4gjvSR2Q0.jpg",
                "2009-09-10",
            ),
            ResultsTvShow(
                91605,
                "I Am...",
                "Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal”.",
                "/oogunE22HDTcTxFakKQbwqfw1qo.jpg",
                "2019-07-23",
            )
        )
    )

    val tvShow = TvShowEntity(
        71712,
        "The Good Doctor",
        "The Good Doctor",
        "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
        "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
        8.6,
        43,
        "2017-09-25",
        "2021-04-19",
        "His mind is a mystery, his methods are a miracle.",
        4,
        "Drama",
        "English",
        "Scripted",
        "http://abc.go.com/shows/the-good-doctor",
        "Returning Series",
    )
}