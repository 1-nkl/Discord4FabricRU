## Начало
Убедитесь, что у вас есть [Fabric Loader](https://fabricmc.net/) и установлен [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api).

Перетащите мод в папку mods вашего сервера (Его можно найти на [Modrinth](https://modrinth.com/mod/discord4fabricru)).

Запустите сервер, подождите пока он загрузится, а потом выключите сервер.

Теперь файл с именем `discord4fabric.json` должен появится в папке `config`

Измените `token` на токен вашего бота . Больше информации [здесь](https://discord.com/developers/docs/topics/oauth2#bots).

Измените `webhook` на URL вашего вебхука (не обязательно). Больше информации [здесь](https://discord.com/developers/docs/resources/webhook#create-webhook).

Измените `guild_id` на айди вашего сервера Discord.

Измените `channel_id` на айди вашего текстового канала (где вы хотите получать сообщения от бота).

Примечание: Этот мод требует `GUILD_MEMBERS` в настройках бота. Пожалуйста, убедитесь что он включен.

## Команды
(Требуется 4 уровень разрешения или OP) `/discord4fabric reload`: Перезагрузить файл конфигурации с диска (Сгенерирует новый файл, если он отсутсвует).

(Требуется 4 уровень разрешения или OP) `/discord4fabric update`: Обновление конфигурации до последней версии.

`/discord4fabric link`: Привязка аккаунта.

`/discord4fabric unlink`: Отвязать связанный аккаунт.

## Заполнители
Заполнители - это способ вставки переменных в настраиваемые текстовые сообщения

Синтаксис: `%category:name%`

## Игрок присоединился/отключился
`%d4f:post_online%`: Количество игроков онлайн после того, как игрок присоединился/отключился. Пример: `42`

Смотрите здесь: https://placeholders.pb4.eu/user/default-placeholders/#player

## Смерть игрока
`%d4f:reason%`: Прчина смерти игрока. Пример: `Steve fell from a high place`

Для получения дополнительной информации: https://placeholders.pb4.eu/user/default-placeholders/#player

## Достижения
`%d4f:title%`: Название достижения. Пример: `Serious Dedication`

`%d4f:description%`: Описание достижения. Пример: `Use a Netherite Ingot to upgrade a Hoe, and then reevaluate your life choices`

Для получения дополнительной информации: https://placeholders.pb4.eu/user/default-placeholders/#player

## Сообщения из Discord в Minecraft
`%d4f:fullname%`: Полное имя пользователя. Пример: `Reimnop#3147`

`%d4f:nickname%`: Ник пользователя. Пример: `Reimnop`

`%d4f:discriminator%`: Тег пользователя. Пример: `3147`

`%d4f:message%`: Сообщение, отправленное пользователем. Пример: `Why did you take the worm from the soup sock?`

### Ответ на сообщение
`%d4f:reply_fullname%`: Полное имя пользователя, которому отвечают. Example: `Reimnop#3147`

`%d4f:reply_nickname%`: Ник пользователя, которому отвечают. Пример: `Reimnop`

`%d4f:reply_discriminator%`: Тег пользователя, которому отвечают. Пример: `3147`

### Кредиты
![Image](https://cdn.discordapp.com/attachments/959467102962610177/983032671229870100/unknown.png)

Для получения дополнительной информации: https://placeholders.pb4.eu/user/default-placeholders/#server

## Сообщения из Minecraft в Discord
`%d4f:message%`: Сообщение игрока. Пример: `MS-DOS wasnt actually coded my Microsoft, but was actually bought` <sub>да, я знаю что это опечатка</sub>

### Если нет вебхука
### Вебхук для обычных сообщений
`d4f:message`: Сообщение после обработки предыдущей опцией конфига.

`d4f:name`: Имя после обработки предыдущей опцией конфига.

Для получения дополнительной информации: https://placeholders.pb4.eu/user/default-placeholders/#player

### Кредиты
![Image](https://cdn.discordapp.com/attachments/959467102962610177/983033944733777920/unknown.png)

Для получения дополнительной информации: https://placeholders.pb4.eu/user/default-placeholders/#player

## Имя Discord
Смотрите здесь: https://placeholders.pb4.eu/user/default-placeholders/#player

## Discord пинг
`%d4f:fullname%`: Полное имя пользователя. Пример: `Reimnop#3147`

`%d4f:nickname%`: Ник пользователя. Пример: `Reimnop`

`%d4f:discriminator%`: Тег пользователя. Пример: `3147`

## Статус бота
Смотрите здесь: https://placeholders.pb4.eu/user/default-placeholders/#server

## Тема канала
Смотрите здесь: https://placeholders.pb4.eu/user/default-placeholders/#server

### Примечание
Предел скорости обновления темы канала слишком высок, поэтому лучше не выставлять интервал обновления темы ниже 6000

Установка интервала обновления темы на -1 отключает её.

## Сервисы аватаров
Чтобы использовать не Crafatar/MCHeads, а что-то другое, измените параметры конфигурации `avatar_url` и `avatar_url_texture_hash`

- `avatar_url` будет заменён на UUID игрока.
- `avatar_url_texture_hash` будет заменен хэшем скина игрока (only used for [FabricTailor](https://www.curseforge.com/minecraft/mc-mods/fabrictailor) compatibility)

## Пользовательские события
Пользовательские события - это чрезвычайно гибкая и расширяемая функция данного мода. Она позволяет создавать пользовательское поведение, определенное в JSON (отправка сообщения, когда игрок присоединяется, выполнение команд, когда кто-то получает повышение и т.д.). Однако это довольно сложно и трудно для понимания. Вы можете найти больше информации об этом [здесь](CUSTOM_EVENTS.md)
