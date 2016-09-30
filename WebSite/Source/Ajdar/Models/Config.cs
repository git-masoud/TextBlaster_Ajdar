using System;
using System.Data.Entity;
using System.Data.Entity.Migrations;
using System.Linq;
using Ajdar.Models;

namespace Ajdar.Models
{



    internal sealed class GameBoardDBInitializer : CreateDatabaseIfNotExists<GameBoardContext>
    {

        protected override void Seed(GameBoardContext context)
        {
            context.Sentences.Add(new Sentence() { Text = "I go to school, go to work and then I go home." });
            context.Sentences.Add(new Sentence()
            {
                Text = "I prefer iced tea but I have to settle for apple juice."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You mustn't touch the picture. The notice says: \"Do not touch\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "When Shelia tried to lift that heavy box she hurt her back."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Has Bob arrived?\"No, if he had arrived, I would have seen him\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "How long has he been doing that puzzle?\"For a few minutes\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I've got two tickets. Which do you want to take? \"None\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I heard him singing in the bathroom.He has a terrible voice"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Could you lend me this book ? I'd like to read it."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I must ask my boss if I can take a day off."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I'm afraid you can't swim now.The water is too cold."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Has David come? \"No, if he had come, I would have seen him.\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Will you come to our party? \"If I have time, I will.\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I arrived in Rome a forthnight ago. That was fourteen days ago."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "This is a good pair of shoes, they will last for ages."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "If you buy half a dozen eggs, you have six of them."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "The bus came after I had been waiting for about twenty-minutes."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "That cinema would be very popular if ithad a car park."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "It’s very hot in the room. Shall I open the window?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Don’t let him order you about, he is not your employer."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "She seemed pleased when she met her cousin for the first time."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You’d better take off your coat if you are too hot."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Mary Green, whose mother you met in my house yesterday, is having singing lessons."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He’s only a boy; how should he know what todo?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "When you open the safe you will see a small black box."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "After it has done 2,000 miles, you can drive your car at 60 mph."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "If you had arrived 10 minutes earlier, you would have got a seat."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "The products of this factory can be sold all over the world."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I haven’t been to the theatre since my child was born."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "We have a few books, but we’d like a lot more."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Couldn’t you go a little faster? I’m in a hurry."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "She is very good at spelling and never makes mistakes in dictation."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I don’t know where he is, he hasn’t arrived yet."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He had to go to a doctor when he was in New York."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Don’t ask for another book until you have finished this one."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Has David arrived yet ? Yes, I saw him come through the door."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I did not recognize him although he said we had met before."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He, unfortunately, can’t come to the party because he is ill."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "When I came back he asked, Did you have a good time ?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You mustn’t strike a match, the room is full of gas."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I am looking for a used car.Could you recommend me one ?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Have you paid the bill ? Yes, I paid it while you were away."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Children shouldn’t be allowed to watch this film, it’s frightening."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I don’t think you will have much difficulty in getting a licence."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Will you take care of the garden while I am in hospital?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "If she had taken the medicine, she would have felt much better."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "From the hill top we had a beautiful view of the Atlantic."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Shall I pay the fares? \"Would you? That's very kind of you.\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Why don't  you turn on the ligth? It is dark in here."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I want to borrow a book from you, you have so many."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Although the question was easy, few boys were able to answer it."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "When we have seen the cathedral we'll go to the museum."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He is just like his father, he never learns from his mistakes."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Children are given too many sweets, it’s bad for their teeth."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Is there enough room for us in the bus ? It looks crowded."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "We should have received the letter yesterday, but it didn’t arrive."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You will be fined for parking your car here. I hope not."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I haven’t got much money at the end of the month."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Where is the police station ? It is three bus stops from here."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You should always turn off the light when you leave the room."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "You drive first, and when you are tired, I’ll take over."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Have you seen Tom lately ? No, he left for Paris last Tuesday."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Mrs Smith wanted to know where I had spent my holidays the year before."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "How long has she been doing her homework ? For half an hour."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Merry Christmas and a Happy New Year! \"The same to you!\""
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "The paper is in the dustbin now, it isn’t on the floor."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "What’s for supper ?There’s fish.It’s on the table near the sink."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "There are only two or three people in the bus with Arthur."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Where do you live?Here, of course. That’s why I am here."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "The book in his hand is interesting, so he is reading it."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He isn’t across the road in the Sunny Snack Bar, is he ?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Where’s Mr Shaw? Is he in?Yes, he’s in his office."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Mr Shaw, George has a headache and he can’t come to work."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Arthur’s father is a doctor in a village in Berkshire called Applefield."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Do you want sugar?Not for me, but lots for Sue."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Have you got a flat there ?No, I live in digs."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He’s going to telephone Arthur because he wants something from the surgery."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Are you going to take him out of the car now, doctor ?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Is he going to be all right ?Yes, I think so."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Arthur has just telephoned Mary and Sheila and told them about Bruce."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I’ve put the meat into the oven and I’ve had a bath."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "She’s going to go back to Middleford in a couple of days."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Sometimes he is still there at seven or eight in the evening."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "They have ordered some special books and these books have already come."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Mary’s not going to go out with Bruce; he’s still in hospital."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Arthur is locking the library doors and Mary’s finishing her last letter."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "That wasn’t a bad meal, was it ?No, it was lovely."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "This weekend, of course, he had enough money for all these things."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "On of the programmes on the television was about learning to drive."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Mr Taylor, your instructor, is out with another pupil at the moment."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "This is your new pupil, Mr Taylor.Mr Taylor Mr Newton."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Put your foot on the accelerator and press down a tiny bit."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "My father’s car didn’t have the gear lever here.It was up here."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "It belonged to our mechanic and he’s looked after it really well."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "They’ll all be surprised to see me with a car, won’t they ?"
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "On Sunday, Arthur took Jennifer and Sheila to Swanage for the day."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "They stopped and looked at Corfe Castle about five miles outside Swanage."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Yes, that’s right, it’s left and then straight on up that hill."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I enjoyed that meal.Didn’t you, Arthur ?Yes, it was good."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "And so Arthur had less money for the rest of his holidays."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "He’s bigger than the others and faster, of course."
            });
            context.Sentences.Add(new Sentence()
            { Text = "Michael is a keen supporter of Middleford Rangers, the local football club." });
            context.Sentences.Add(new Sentence()
            {
                Text = "Michael had a spare ticket for Saturday’s home match against Didcot United."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I suppose, if you think about it, I’m a Didcot supporter really."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "Every week Arthur received a coupon by post for the football pools."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "She was watching Arthur and noticed that he was getting very excited."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I had a beautiful piano but I sold it in the end."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "I work in a workshop, but it is not in a factory."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "There is life on Earth, but there isn’t life on the Moon."
            });
            context.Sentences.Add(new Sentence()
            {
                Text = "In this country there are a lot of rich and poor people."
            });


            base.Seed(context);
        }
    }
}