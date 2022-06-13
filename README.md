## Welcome to DiscBuddy, a fun little bot that will try to brighten up your day.

### Created using Java and [JDA](https://github.com/DV8FromTheWorld/JDA)

### Configuration

Since I will not be able to keep this bot online 24/7, I have no problem with it being configured by other people. So please feel free to do so, but you need to add your own keys and tokens!

- This Bot uses [NASA's API](https://api.nasa.gov/) and [thecatapi.com](https://thecatapi.com/) to provide images of space and cats. Create an `.env` file in the root directory of the project and add the following lines:

```
DISCBUDDY_KEY=<your_key>
NASA_KEY=<your_key>
CAT_KEY=<your_key>
```
### Install

[Go to this link to add DiscBuddy to your Discord server](https://discord.com/api/oauth2/authorize?client_id=975915701476917288&permissions=8&scope=bot)

### Commands

The bot will respond to the following commands:

- `!help` - Displays all the commands the bot can respond to.
- `!register` - The bot will register you as a user in the database.
- `!unregister` - The bot will unregister you as a user in the database.
- `!check` - The bot will check if you are registered in the database.
- `!time` - Displays the current time and date in the server.
- `!space` - Displays a random image of space.
- `cat` - The bot listens for the word 'cat' (and other similar words!) and responds with a random gif of a cat.
- And whatever you do, don't say anything bad about the bot.
