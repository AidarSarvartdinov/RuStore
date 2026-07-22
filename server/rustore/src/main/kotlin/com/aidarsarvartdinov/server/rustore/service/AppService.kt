package com.aidarsarvartdinov.server.rustore.service

import com.aidarsarvartdinov.server.rustore.dto.AppDetails
import com.aidarsarvartdinov.server.rustore.dto.AppSummary
import com.aidarsarvartdinov.server.rustore.dto.Category
import org.springframework.stereotype.Service

@Service
class AppService {

    fun getApps(): List<AppSummary> = mockAppSummaries

    fun getAppsByCategroy(categoryName: String): List<AppSummary> {
        return mockAppSummaries.filter { it.category == categoryName }
    }

    fun getAppDetails(id: String): AppDetails? {
        return mockApps.find { it.id == id } ?: throw RuntimeException("App not found")
    }

    fun getCategories(): List<Category> = categories

    fun searchApps(query: String): List<AppSummary> {
        val trimmed = query.trim()

        return if (trimmed.isEmpty()) {
            mockAppSummaries.sortedByDescending { it.downloads }.take(10)
        } else {
            val filtered = mockAppSummaries.filter { it.name.contains(trimmed, ignoreCase = true) }
            filtered.ifEmpty {
                mockAppSummaries.sortedByDescending { it.downloads }.take(10)
            }
        }
    }

    private val mockApps = listOf(
        AppDetails(
            id = "sber",
            name = "СберБанк Онлайн",
            description = """
                СберБанк Онлайн — ваш надёжный помощник в ежедневных делах. Все финансовые и нефинансовые возможности доступны в одном приложении: от быстрых платежей и удобных переводов до сервисов для жизни, покупок и путешествий.

                На главном экране собраны самые нужные финансовые инструменты. Баланс карты, переводы, история операций, информация о бонусах Спасибо и расходы за месяц — все действия доступны в несколько кликов.

                В Накоплениях удобно управлять долгосрочными сбережениями: вкладами, счетами, инвестиционными продуктами. Сервисы аналитики помогут с оценкой доходности портфеля за месяц, квартал или год. А если хочется за конкретное время накопить на мечту — можно установить цель.

                В разделе «Для жизни» собрана вся нефинансовая экосистема Сбера и партнёров: выгодные покупки, развлечения, путешествия, здоровье и подборки для вдохновения — что посмотреть, куда сходить. А ещё каждую неделю здесь можно поймать самые выгодные предложения.

                В Платежах легко пополнить баланс телефона или оплатить интернет. Здесь же собраны сервисы для дома и авто: быстрая оплата ЖКУ и штрафов, информация по ипотеке и автокредиту, помощь с ремонтом, заправка за бонусы Спасибо и многое другое.

                В разделе «Кредиты» есть всё, что поможет стать на шаг ближе к цели: управление действующими кредитами, предстоящие платежи, возможности для получения дополнительных денег от банка и проверка кредитной истории.

                С помощью поиска легко найти любой сервис, а если запрос сложный, можно обратиться к ИИ-помощнику GigaChat. Он ответит на вопросы о финансах, даст советы по планированию или структурирует сложную информацию.

                А ещё интерфейс приложения можно легко настроить под свои задачи: поменять местами разделы, скрыть неактуальное или добавить то, чем важно управлять сейчас. Изменить СберБанк Онлайн под настроение тоже возможно: достаточно выбрать фон приложения, тему и установить аватар.

                ______

                Если вы нашли ошибку в приложении, хотите что-то предложить или сказать спасибо, поставьте оценку и напишите комментарий. Мы всё внимательно читаем и стараемся улучшать СберБанк Онлайн на основе ваших откликов.

            """.trimIndent(),
            category = "Финансы",
            developer = "ПАО Сбербанк",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/qriFjN8OV6VBF4CCbWcxPm7SL0Y0YtMfxTeJSzWZ1Rc/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/lnmBFdQzeQzn1S0Ge3oIo3Cb5pD_k52ZUQj_IEvUpAw/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/9/7f/apk/462271/content/SCREENSHOT/fff6b158-9e1e-43ae-a9d9-2c4c5beb63e9.png@webp",
                "https://static.rustore.ru/imgproxy/5TET25z17NrXqqKu6HZL-Hui5OASHSW6quc3Djsxw8s/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/9/80/apk/462271/content/SCREENSHOT/20a1601e-a7bd-40db-ac1f-6932edcb1175.png@webp",
                "https://static.rustore.ru/imgproxy/8sWJJtacu_dKPFE-Ig6hP-EcF-Ztj3Y7OKToZuyRjTk/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/9/b5/apk/462271/content/SCREENSHOT/d3fc8c63-5813-44b3-a421-5efe5e4db02c.png@webp",
                "https://static.rustore.ru/imgproxy/u1pm05AeLpnZ5MM92Ip3ThL5JoNHebUZDxkCtkrsV9E/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/9/0d/apk/462271/content/SCREENSHOT/d6aed39d-90dd-4c2f-89f9-72c808a117ba.png@webp",
                "https://static.rustore.ru/imgproxy/JPUBFWe9KqAY0nNnLSigN3ZFujODI2SBicRdwwaLJiE/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/9/64/apk/462271/content/SCREENSHOT/dc7e7c0f-421b-48d8-a016-f921cccfc64c.png@webp"
            ),
            downloads = 80_000_000
        ),
        AppDetails(
            id = "vtb",
            name = "ВТБ Онлайн",
            description = """
                Мобильное приложение ВТБ Онлайн — современный и безопасный цифровой банк, который всегда под рукой. В любой момент вы можете быстро и удобно совершать платежи и переводы, контролировать счета и управлять своими финансами.

                🔒 Безопасность на первом месте
                Войти в приложение можно по номеру телефона, коду из СМС или биометрии — выбирайте удобный способ для себя. Для онлайн-защиты установите определитель номера и самозапреты на снятие наличных, кредиты и другие операции, подключите сервис «защита близких» и используйте «тревожную кнопку» для сообщения о мошеннических операциях. Больше информации о том, как себя обезопасить, читайте в разделе «Безопасность».

                🌍 Быстрые переводы по миру
                Совершайте переводы в 150+ стран мира. Переводы за рубеж доступны по номеру телефона, международной карте или по номеру счета в национальных валютах. Деньги также можно получить наличными — нужен только паспорт и ближайший пункт выдачи.

                📸 Удобные платежи без комиссии
                Оплачивайте товары и услуги без комиссии по QR-коду или фото, из мессенджеров, галереи и почты через умную камеру, просто отсканировав счет. Также вы можете подключить автоплатежи на услуги ЖКХ и мобильную связь.

                👪 Выгода — дело семейное
                Объединяйтесь в группу с близкими и получайте дополнительные преимущества: 5% кешбэк за покупки в супермакетах (для новых участников), до 7% на ежедневный остаток по семейному счету, чтобы вместе копить и тратить. Сервис «Мои балансы» в режиме реального времени показывает, сколько денег осталось на балансе мобильных телефонов ваших близких, состоящих в группе, и помогает вовремя пополнять их счета.

                💳 Бесплатная дебетовая карта
                Оформите бесплатную дебетовую карту или платежный стикер прямо в приложении за пару минут — забрать можно в удобном офисе или заказать доставку курьером.

                💳Зарплатная карта с выгодой
                При переводе зарплаты в ВТБ вас ждут: дополнительная категория кешбэка, надбавка к ставке по накопительному счету и другие привилегии.

                📈 Финансы под контролем
                Заказывайте справки и выписки по счетам, управляйте лимитами на переводы и платежи, отслеживайте свои поступления, расходы и кешбэк за операции. Все ваши финансы под защитой.

                🤝 Умный поиск и поддержка 24/7
                Пользуйтесь умным поиском, чтобы быстрее находить нужные разделы и сервисы. По всем вопросам вам всегда доступна поддержка по телефону или через звонки в приложении, а также в чате ВТБ Онлайн и в официальном сообществе банка во «ВКонтакте». Также в чате теперь есть «Каналы», где можно узнать актуальные новости приложения и поймать свою выгоду.
        """.trimIndent(),
            category = "Финансы",
            developer = "Банк ВТБ (ПАО)",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/lzg_3CHk-oqIqIfBj0KFQ-vhwK6NUJk6qC1r1Lt0fKM/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/3/28/37/apk/404415/content/ICON/577a3521-f7ef-4322-ab3c-c96b9262c562.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/85zWOywv0_pqbtzEMV4wnmAMhDIPD3dJNT0FV95-Y24/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/3/28/12/apk/404415/content/SCREENSHOT/a0a1e69a-38c0-4d1a-a131-00ef13185ff6.png@webp",
                "https://static.rustore.ru/imgproxy/OzzZz8w6OMU-3FwDPiqDihqe6Q9ZY2EL5WpG4PsMrQk/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/3/28/53/apk/404415/content/SCREENSHOT/fc38e187-d67b-44fd-8311-df364cb41407.png@webp",
                "https://static.rustore.ru/imgproxy/4ReOGJQt-k0dHKdaLMKQfLTx7-1yw6l_XKlh-zM8z4U/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/3/28/69/apk/404415/content/SCREENSHOT/e7dd16b6-d853-4268-9a2d-f9d947d96ce8.png@webp",
                "https://static.rustore.ru/imgproxy/G15KdheDlG4j6TiHAOvhBzpwuKTRtV1sLx5D8c-Y-uc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/3/28/e8/apk/404415/content/SCREENSHOT/3ec7ee40-fc0c-4c1f-9e8a-5b75d51c6f84.png@webp",
                "https://static.rustore.ru/imgproxy/WqS0m5LtbmVWUKOSN7UmqHLFE9a6Dv9JopJCxfI76QM/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/3/28/ae/apk/404415/content/SCREENSHOT/b72c1efa-470b-4450-8f0e-9b0b00bcb2d5.png@webp"
            ),
            downloads = 40_000_000
        ),
        AppDetails(
            id = "tbank",
            name = "Т-Банк",
            description = """
            Т-Банк — приложение для управления деньгами и картами онлайн. Оплачивайте ЖКХ, штрафы ГИБДД и госуслуги без комиссии, переводите через СБП, копите на вкладах и инвестируйте — всё под рукой.

            ✅ Карты, вклады и инвестиции
            
            Дебетовая карта Black — пластиковая МИР или виртуальная. Оплачивайте счета бесконтактно и телефоном, получайте кэшбэк рублями за покупки. Если нужны дополнительные деньги, подойдет кредитка Платинум с лимитом до 1 000 000 ₽.
            
            Откройте вклад или накопительный счет, чтобы деньги работали. Если хочется большего, инвестируйте в акции российских компаний через брокерский счет прямо в приложении. Если не хочется выбирать ценные бумаги самому, есть готовые стратегии от аналитиков. Начать можно с 10 ₽.
            
            ✅ Страхование
            
            Оформите ОСАГО, каско или страховку имущества — без очередей и лишних документов.
            
            ✅ Кэшбэк и выгода
            
            Каждый месяц выбирайте 4 категории повышенного кэшбэка до 15%. В приложении более 100 предложений от известных брендов с кэшбэком до 30%, а в рубрике «кэшбэк дня» — лучшее предложение на сегодня.
            
            Покупайте билеты, заказывайте еду, заправляйте машину — всё с кэшбэком до 30%. Платите долями или в рассрочку — без переплат. Подписка Pro и сервис Premium увеличат выгоду от каждой траты.
            
            ✅ Управление деньгами и бюджетом
            
            Оплачивайте налоги, ЖКХ, квартплату и штрафы без комиссии. Переводить деньги и оплачивать счета онлайн удобнее с умной камерой: распознает номера карт, квитанции, QR-коды и написанные от руки номера телефонов.
            
            В разделах Дом и Авто собраны траты по этим тематикам, плюс оценка рыночной стоимости машины и квартиры. Меняйте категории операций, чтобы точнее вести бюджет и следить за расходами, или доверьте это сервису Финздоровье. Он считает общий баланс электронного кошелька, показывает кредитный рейтинг и дает рекомендации, что делать с деньгами.
            
            Пополняйте копилку и откладывайте на цели: функция «Письмо себе» напомнит, стоит ли тратить деньги с накопительного счета. Если хотите скрыть балансы в публичном месте — переверните телефон.
            
            AI-помощники дадут рекомендации, выполнят поручения и подстроятся под ваш стиль общения.
            
            ✅ Защита денег и данных
            
            «Защитим или вернем деньги» определяет мошенничество во время звонка с вероятностью выше 99%. А если мошенники всё же получат деньги, мы их вернем. Работает вместе с сим-картой Т-Мобайла,
            
            «Защита близких» помогает уберечь деньги семьи от мошенников: переводы и кредиты родных проходят с вашего согласия.
            
            T-ID — быстрый и безопасный вход на сайты и в приложения: сохраняет пароли и автоматически заполняет анкеты.
            
            Тарифы и условия по продуктам банка: https://www.tbank.ru/app-store/
        """.trimIndent(),
            category = "Финансы",
            developer = "Т-Банк",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/qqoP1Cyi4tplnwb8Z_yJ2mnWVhuen4kzin6tQLWsSHY/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/220863/content/ICON/2238e3ca-e3e7-41d0-b037-777ddc637a5b.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/pGCkIfaj1QDM9M07Zc-1JzrFtmEJNoKOamxIcLQBJv0/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/f1/apk/220863/content/SCREENSHOT/a99db8d2-1771-49b9-9dec-b965184a42a6.png@webp",
                "https://static.rustore.ru/imgproxy/1tIurY6QRC4MP-hlf__AGivKE07Gq0bkJcRFtdYZM1o/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/af/apk/220863/content/SCREENSHOT/2811bcf2-5e0e-4cd6-a7d5-a27cdd351f33.png@webp",
                "https://static.rustore.ru/imgproxy/waeaJ8NC9LR8cyW81FCE684Pz_qnJH4QeEK9Gb7cdS8/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/2a/apk/220863/content/SCREENSHOT/52a7432c-ca4d-4952-95cb-5b793764c894.png@webp",
                "https://static.rustore.ru/imgproxy/WDv1a3r7gvKmqAwL8KIZLepTgMOSM4jL2VXhNXSuyzs/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/f8/apk/220863/content/SCREENSHOT/59a2589b-29f0-4bcd-b8f8-e36b6f393c47.png@webp",
                "https://static.rustore.ru/imgproxy/GGXVxcP3I1qMpt_0I2_8ReBIUlU_bZ6WJjjIqJL5akc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/24/apk/220863/content/SCREENSHOT/8228964f-e741-4445-aca9-47cca4bb81c0.png@webp",
                "https://static.rustore.ru/imgproxy/b9OBTcMbxpGNooRZakdTCDCSowhrFD8wALfB5z9maow/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/30/86/apk/220863/content/SCREENSHOT/b4d35726-6ca7-4e88-bbe6-932dbecfcede.png@webp"

            ),
            downloads = 35_000_000
        ),
        AppDetails(
            id = "alfa",
            name = "Альфа-Банк",
            description = """
                Лучший мобильный банк пять лет подряд по версии Markswebb

                Мобильное приложение Альфа-Банка — это безопасный доступ к вашим счетам и банковским картам. В любой момент вы можете сделать быстрый перевод близкому человеку, проверить, сколько денег осталось, оформить кредит онлайн или пополнить счёт мобильного.

                Платить онлайн — легко и приятно. Реквизиты карт всегда доступны в приложении, и нет комиссии за оплату коммунальных платежей, телефона и других услуг. А переводить — выгодно: до 100 000 ₽ в месяц бесплатно по номеру телефона в любые банки.

                🛡 Ваши деньги под защитой

                В приложение можно войти по пин-коду, с помощью распознавания лица или отпечатка пальца — выбирайте любой удобный способ защиты для вас.

                ⚡Мгновенный выпуск карты

                Нужна карта — получите сразу в приложении. Добавьте её в Google Pay или Samsung Pay и тут же оплачивайте покупки с помощью телефона. Если понадобится пластиковая карта, бесплатно привезём её домой, на работу или в ближайшее отделение.


                👌 Счета и карты под контролем

                Можно открывать счета и закрывать старые, блокировать карты и выпускать новые, открывать депозиты, валютные и накопительные счета — всё без похода в отделение. Если едете в путешествие за границу, меняйте счёт карты с рублёвого на валютный и обратно — так не потеряете деньги на конвертации.

                👀 За бюджетом легко следить

                Сколько вы потратили и заработали — покажет раздел «История». Рядом с покупкой увидите, какой кэшбэк за неё получили. Если нужно найти платёж или пополнение, помогут фильтры по датам и по типу.

                🔔 Напоминание о платежах

                Настраивайте автоплатежи и создавайте шаблоны для регулярных переводов или однотипных трат. Приложение напомнит, когда пришло время платить, и вам не придётся вручную вводить реквизиты.

                💵 Оплата вовремя и без комиссии

                ЖКХ, телефон, интернет, налоги, штрафы гибдд — всё можно оплатить в приложении без комиссии. Сохраните реквизиты — тогда приложение покажет, когда придёт новый счёт на оплату.

                📱Переводы по номеру телефона

                Альфа-Банк подключён к Системе Быстрых Платежей — можно бесплатно переводить 100 000 ₽ в месяц в другие банки. Переводы с карту на карту, по номеру телефона и счёта, QR-коду и реквизитам тоже есть.

                🚀 Не только карты

                В приложении вы можете оформить страховку (жизни и здоровья, квартиры, имущества, для путешествий и прочее), а также начать инвестировать. Предодобренную заявку на кредит или кредитную карту тоже можно заполнить через приложение — это удобно и не нужно никуда идти.

                👋 Если нужен оффлайн

                В приложении есть карта ближайших отделений и банкоматов. Разрешите доступ к геопозиции и выберите нужное — будем рады вас видеть.


                Мы стараемся сделать приложение ещё лучше, и в этом нам помогают ваши отзывы и предложения. Присылайте их на store@alfabank.ru.
        """.trimIndent(),
            category = "Финансы",
            developer = "АО \"Альфа-Банк\"",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/FjgsClgg0crUVE9DiaueyRVtmQQMrZ7fYyNh88Gedq0/preset:web_app_icon_160/plain/https://static.rustore.ru/afb07f02-5399-4f45-a366-e49a7b3420ad@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/VzcxVSXLJmC7fxIRc-cU7UPfbtjv26Nb66pXVZNclBE/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/558271/content/SCREENSHOT/d4617777-6a11-4b0e-bc2a-20a59fa8964a.jpg@webp",
                "https://static.rustore.ru/imgproxy/zB9N7Y7WBCjs8X2pWqblbUgVwXTI7Nq9RPBvVb999zk/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/558271/content/SCREENSHOT/6c8610f0-93a7-4029-8359-54fc14d5dbcb.jpg@webp",
                "https://static.rustore.ru/imgproxy/8PDgv-E4efGWmLBdLsMCwqRrZYXi2MvRz6BY6pPV_TU/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/558271/content/SCREENSHOT/71af38fb-4b13-4858-bfc4-a532890adbf4.jpg@webp",
                "https://static.rustore.ru/imgproxy/BMDSkjZLhCM0DPEvQH6PkteikhrJc8PNfOHQFoC7Law/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/558271/content/SCREENSHOT/cd7b85f8-6005-4b89-992a-fdc01d818991.jpg@webp",
                "https://static.rustore.ru/imgproxy/KQ_F85WVKUVWftc-e8jksac7FOULvkjUeXhNXHUkCOg/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/558271/content/SCREENSHOT/130bca2c-a5a1-45b4-9449-006b54bfb9e1.jpg@webp"
            ),
            downloads = 20_000_000
        ),
        AppDetails(
            id = "gazprom",
            name = "Газпромбанк",
            description = """
            Приложение Газпромбанка — это все нужные банковские сервисы в вашем мобильном телефоне: информация о картах, переводы, платежи и многое другое. Вы сможете открывать счета и вклады, оформлять и погашать кредиты, менять валюту — и все это без визитов в офис банка.

            ЕЩЕ НЕТ КАРТЫ? Стать клиентом банка просто — установите приложение и закажите карту. Можно выбрать из четырех программ лояльности и получать кешбэк до 30% за покупки у партнеров. Обслуживание всегда 0 ₽ в месяц без всяких условий.

            ПРОСТАЯ РЕГИСТРАЦИЯ. Понадобятся только данные карты Газпромбанка и номер телефона или пароль от интернет-банка.

            КАРТЫ И СЧЕТА. Оформляйте новые дебетовые и кредитные карты: цифровые выпускаются за несколько минут, а пластиковые доставим бесплатно. Вы сможете блокировать и активировать карты и устанавливать на них лимиты, открывать счета в шести валютах. Реквизиты карт и счетов всегда будут под рукой, и в любой момент можно заказать выписку.

            ПЛАТЕЖИ. Оплачивайте услуги ЖКХ, мобильную связь, интернет, транспортные карты, страховку, штрафы ГИБДД, детские сады, школы и другие услуги — по QR-коду или реквизитам. Сохраняйте шаблоны и автоплатежи для регулярных операций.

            ПЕРЕВОДЫ. Переводите деньги между своими счетами и картами, а также другим людям по номеру телефона, карты или по реквизитам. Можно отправлять переводы юрлицам и бюджетным организациям.

            НАКОПЛЕНИЯ. Открывайте вклады и накопительные счета в приложении — это займет несколько минут. Информация о накоплениях будет всегда доступна в вашем мобильном, закрыть счет или вклад также можно без визита в офис банка.

            ОБМЕН ВАЛЮТЫ. Покупайте и продавайте валюту в любое время. Чем больше сумма обмена, тем выгоднее курс.

            КРЕДИТЫ. Оформляйте кредиты прямо в приложении, в офис ехать не нужно.
        """.trimIndent(),
            category = "Финансы",
            developer = "Газпромбанк",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/d2jYeSCJEbfYBmccR5nYdUpuyVaCFCJSzpSNCi2d-Tc/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/3/31/bd/apk/811199/content/ICON/0660e89e-0417-44fb-bb9b-ba42357a0a2b.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/lXHJRD_Lrj_g9rmDeM99BI3NrUCDlpuNWFXUBw7LCcM/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/12/zz/apk/811199/content/SCREENSHOT/bfd61abf-44d1-48d5-969f-88a3c27d18ee.png@webp",
                "https://static.rustore.ru/imgproxy/pgbemw8PcPFeBfG_08TM8a2Q0P7GYnSjkr9KhoZja_o/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/12/61/apk/811199/content/SCREENSHOT/51a18f4f-9758-4895-969b-ed22c40412a1.png@webp",
                "https://static.rustore.ru/imgproxy/7Ankn9kvom_6RSXicv9wOjd2JgkkyJVeG53yu4V2PWQ/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/12/47/apk/811199/content/SCREENSHOT/ba0b0a25-d4c4-4f7b-b638-96e657a25a6e.png@webp",
                "https://static.rustore.ru/imgproxy/kCcIpMZNkzoMwlssmrqUdQ0blCdMFp3cNCQj6XMseDg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/12/a4/apk/811199/content/SCREENSHOT/6533a333-cc94-47b4-81c2-9a79e39c6dc2.png@webp",
                "https://static.rustore.ru/imgproxy/VhrroFaIyUItRCUx1OsXUCnhgKt6j-9DFFK96lCfMgU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/12/21/apk/811199/content/SCREENSHOT/7a187d69-0060-41c9-b893-4deafd7814a9.png@webp"
            ),
            downloads = 10_000_000
        ),
        AppDetails(
            id = "yandex_browser",
            name = "Яндекс Браузер с Алисой AI",
            description = """
            Яндекс Браузер — быстрый и безопасный браузер с Алисой AI и выгодными покупками. Нейросети в Яндекс Браузере Алиса AI умеет решать повседневные задачи: подскажет погоду, посоветует, где поесть или купить продуктов. Она всегда рядом — поможет с вашей задачей и подскажет нужное в поисковой строке. А ещё бесплатно сгенерирует любую картинку, которую вы попросите.

            Начать чат с Алисой AI можно на любой странице в интернете. История всех чатов сохраняется на главной странице и доступна с любого устройства, где есть Браузер. Алиса AI в Поиске отвечает быстро, понятно и со ссылками на источники. А Умная камера поможет найти по фото что угодно в интернете.

            Встроенный редактор с Алисой AI пишет, редактирует и переводит тексты. А ещё кратко пересказывает видео, статьи и документы. Даже очень большие. Браузер переводит иностранные видео с восьми языков: английского, испанского, французского, немецкого, итальянского, китайского, корейского и японского.

            Вкладки и группы можно добавлять в облако — и они появятся везде, где вы залогинены в Браузере. Технология активной защиты Нейропротект предупреждает пользователей об опасных сайтах и страницах с платными мобильными подписками. Автоматический определитель номера подскажет, кто звонит, даже если номера нет в контактах вашего телефона, а также поможет блокировать незнакомые номера.
        """.trimIndent(),
            category = "Инструменты",
            developer = "ООО «ЯНДЕКС»",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/bZNt9jiZUOVXXOG0JdJQleTYIB2cFeE3MaWk7o897jE/preset:web_app_icon_160/plain/https://static.rustore.ru/2025/10/25/1e/apk/579007/content/ICON/939321c0-03f7-484d-9043-c0fb12736ef1.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/PFLM-GlJd_frNCL5vR_OJZZQhzdL3fvgdvIdpJrVq4A/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/6a/apk/579007/content/SCREENSHOT/b14e7901-1fcb-4045-94af-3464c359f224.jpg@webp",
                "https://static.rustore.ru/imgproxy/Au3P2Niy_5KX1We1FiE018ua2_ESFPF4ojmIq8hLrew/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/bc/apk/579007/content/SCREENSHOT/eb4422a7-36cf-4d11-a25a-456026f39cc7.jpg@webp",
                "https://static.rustore.ru/imgproxy/Fs5c_h_5CDN9x08e-7hwqtRjLp-SHCktgtEr54qUfEE/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/81/apk/579007/content/SCREENSHOT/1d9d8a7f-9d6d-4b53-9107-c379bbd1ce48.jpg@webp",
                "https://static.rustore.ru/imgproxy/wAYoW7mF9FERBTTrYt6bEodhDIRSa8WZIcgYfGk3oxU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/b3/apk/579007/content/SCREENSHOT/816a1cc0-31aa-431f-a560-51aad66f7342.jpg@webp",
                "https://static.rustore.ru/imgproxy/7WvsvWYNOL96eLz05zgI3XrWEBCGd4QZ8-UUL7vrcSc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/5d/apk/579007/content/SCREENSHOT/73f275e3-9c51-42a0-91de-d42cf2254a06.jpg@webp",
                "https://static.rustore.ru/imgproxy/_cSNJhYj0g9c6cO3jKpLNYf-NeBTh-KWaMHnuV3euDE/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/e8/apk/579007/content/SCREENSHOT/e646d9c9-8ee4-409d-b9c3-79badfeecc7f.jpg@webp",
                "https://static.rustore.ru/imgproxy/Ipj_ojaW8i4bDbV-LF7oyWFeWyXxvBifuy5W5wzmpN0/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/c8/apk/579007/content/SCREENSHOT/d92ebe80-fd12-4d0c-a779-7b4c872e8a2b.jpg@webp",
                "https://static.rustore.ru/imgproxy/pd_47c1oQQjCWvZyE1d6_qrqfrEhQIPn9XHHEWHHeTY/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/9a/apk/579007/content/SCREENSHOT/eef7e475-741d-406b-95b1-fe71cbf80e05.jpg@webp",
                "https://static.rustore.ru/imgproxy/g5dduk32Xq8khm-5zvdICMr8sBRLiWe00EcyesdCweg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/25/c4/apk/579007/content/SCREENSHOT/a4eb0258-b63d-4ae5-9cb5-8a8f62e14b1d.jpg@webp"
            ),
            downloads = 40_000_000
        ),
        AppDetails(
            id = "avatan",
            name = "Avatan - Фоторедактор и Ретушь",
            description = """
            Avatan — это фоторедактор с расширенными социальными возможностями. Приложение предоставляет пользователям возможность совместно редактировать снимки, используя уникальные элементы для обработки. Эти элементы, созданные самими пользователями, постоянно добавляются в социальную сеть, тесно интегрированную с функционалом фоторедактора.

            Эффекты: Вы получаете уникальную возможность создавать и сохранять собственные оригинальные эффекты. Для их разработки доступен широкий спектр инструментов и ресурсов.

            Ресурсы: При редактировании фотографий вы можете добавлять и применять четыре различных типа ресурсов: Наклейки, Текстуры, Рамки, Фоны.

            Ретуширование: Фоторедактор также включает в себя все необходимые инструменты для профессиональной ретуши лица и коррекции форм тела. Эти функции позволяют легко и эффективно устранять любые несовершенства, обеспечивая высококачественный результат.

            Вы можете сохранять любые понравившиеся эффекты и ресурсы в раздел «Избранное», чтобы впоследствии оперативно применять их к новым снимкам. Благодаря обширному выбору разнообразных инструментов и возможностей, каждая обработанная вами фотография будет выглядеть качественно и уникально. Ежедневно множество пользователей активно пополняют свои коллекции, добавляя новые эффекты и ресурсы для обработки фотографий на самые актуальные и интересные темы.

            Интуитивно понятный и удобный интерфейс позволяет выполнять даже сложную обработку изображений с помощью простых действий, превращая весь процесс в истинное удовольствие.
        """.trimIndent(),
            category = "Инструменты",
            developer = "Avatan Studio LLC",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/QwcfxgP68QEUN2r8BsXqmw3ks9zr6sy-KqkaIBKuW1c/preset:web_app_icon_160/plain/https://static.rustore.ru/2025/10/6/7a/apk/2063662687/content/ICON/f5ccce4e-9186-4f51-8560-84a8072ddeb6.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/Gke1V3gqz0Wz7G2xDUskO0nxvrsflp1vWbA5z2tgtvM/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/6/ba/apk/2063662687/content/SCREENSHOT/b36f72b5-943f-4f9e-8b5a-693e2f49a765.jpeg@webp",
                "https://static.rustore.ru/imgproxy/yoorB9XG_7LECphc-T6CWOI8UF9nJxXgs6RFf3jFwmo/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/6/81/apk/2063662687/content/SCREENSHOT/ee1ddc53-5845-4350-9559-a6752774e7c8.jpeg@webp",
                "https://static.rustore.ru/imgproxy/g5xavDBejyV2a3WBr8KN8_ZccKTu-I-LjKTIAnrqxmI/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/6/bf/apk/2063662687/content/SCREENSHOT/f779cd0b-92c6-4a42-a317-b990722caa10.jpeg@webp",
                "https://static.rustore.ru/imgproxy/W14GB12d0PGKqeMWwvygYpbxlrd4aJSoYeYFFBBdwtQ/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/10/6/fe/apk/2063662687/content/SCREENSHOT/7bf70230-a40c-4b71-b2d1-c9dbdd6f4152.jpeg@webp"
            ),
            downloads = 3_000
        ),
        AppDetails(
            id = "mailapp",
            name = "Mail: Почта, Облако, Календарь",
            description = """
                Почта Mail — ваш быстрый и удобный почтовый клиент.

                Добавьте все свои аккаунты и читайте электронную почту с ящиков на Mail, Яндекс, Microsoft Outlook почта, Gmail от Google, Rambler, Yahoo и других сервисов. Отправляйте письма, записывайте дела в рабочий или личный календарь, загружайте фото и документы на диск в Облако Mail. Звонки, новости и погода — всё в одном приложении!

                • Несколько аккаунтов. Соберите все почты в одном приложении, чтобы легко находить любые письма. Можно подключить ящики на Outlook, Yahoo, Gmail почта от Google, Яндекс, Rambler и Zimbra.

                • Полезные сервисы. Чтобы все дела были под рукой, в приложении не только быстрая почта, но и рабочий календарь, задачи, звонки, Облако Mail для хранения фото и других файлов. Лента новостей, прогноз погоды, оплата штрафов, переводчик писем — всё необходимое в одном месте.

                • Планер: календарь и задачи. Назначайте встречи, ставьте напоминания, записывайте дела, ведите рабочий или другой тематический календарь. Создавайте ссылку на звонок одним нажатием, когда добавляете встречу в рабочий календарь.

                • Видеозвонки. Позвонить коллегам и друзьям, чтобы обсудить дела и поболтать, можно двумя способами. Создайте ссылку на звонок и соберитесь, когда удобно всем. Или выберите человека из контактов и звоните напрямую.

                • Облако Mail — хранилище для документов и фото. Облако хранит файлы из писем, находит сканы документов и собирает вместе, чтобы они всегда были под рукой. Загрузите фото на диск в Облако Mail, чтобы освободить память смартфона.

                • Перевод на другие языки. Добавили удобный переводчик электронной почты. Пригодится, если читаете иностранную рассылку или отправляете бизнес-почту коллеге за границу. Просто откройте рассылку или напишите текст и выберите язык — перевод отобразится мгновенно.

                • Отписка от всего лишнего сразу. Ваши рассылки удобно собраны на одной странице. Можно мгновенно проверить, что вам приходит, удалить ненужное или отписаться.

                • Тёмная тема и фоны. Яркие и спокойные фоны украсят Почту Mail, а тёмная тема поможет комфортно работать даже ночью.

                • Контакты. Приложение собирает в адресной книге контакты из вашей почты и с телефона. Все адреса под рукой — просто выберите, кому позвонить или написать. Переключайтесь между почтовыми ящиками на Rambler, Gmail почта от Google, Яндекс, Outlook, Yahoo и добавляйте новые контакты!

                • PIN-код. Электронная почта, фото и контакты под защитой — поставьте PIN-код для входа, чтобы в почтовый клиент не попал никто кроме вас.

                • Уведомления о новых письмах. Настройте уведомления под себя: поставьте время, выберите папки и людей или скройте превью. Уведомления не разбудят вас ночью, ведь Почта Mail заглушает звук во время сна.

                • Группировка писем. Приложение Почты собирает в цепочки переписку с одной темой и получателями. А ещё автоматически сортирует по папкам рассылки, новости, уведомления от соцсетей и письма себе.

                • Быстрые действия с письмом. Проведите по письму справа налево, чтобы увидеть действия: отметить прочитанным, поставить флаг, переместить или удалить.

                • Офлайн-режим. Приложение сохраняет письма в памяти телефона, чтобы их можно было смотреть без интернета. Читайте письма и просматривайте фото даже в самолете или за городом.

                Условия использования: https://help.mail.ru/legal/terms/mail/ua/
                Политика конфиденциальности: https://help.mail.ru/legal/terms/mail/privacy/
                Поддержка: https://trk.mail.ru/c/ps7n55

                ЕЩЕ ПАРА СТРОК
                Почта Mail — простой и надёжный почтовый клиент. Приложение совместимо с Android версии 6.0 и старше. Подходит для работы с почтовыми ящиками Mail.ru, Яндекс, Rambler, Gmail почта от Google, Yahoo, Outlook и других сервисов, поддерживающих протоколы IMAP, POP и SMTP.
            """.trimIndent(),
            category = "Инструменты",
            developer = "VK",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/2wnsbc-wCmdbFYEdpH8uL3Jl4db6i7HE9Vj5079oh6Q/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/3/11/7c/apk/332223/content/ICON/2ea61211-2ee2-469b-a08e-acc8a9f3b4c6.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/dRrOi-XMGMWPaH8lXFcYeQjeJqZZu6dYfidYFPaYgJc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/01/apk/332223/content/SCREENSHOT/3e6493e1-f8de-4c16-96a2-2a2c7a8df216.png@webp",
                "https://static.rustore.ru/imgproxy/drYmGHHOFnKdnmAnSD4Rnf_nvPuPRarPeu_sPUt5V5c/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/78/apk/332223/content/SCREENSHOT/236ed548-d10b-4765-82ae-2195e4d0b83f.png@webp",
                "https://static.rustore.ru/imgproxy/mBuseI6V3KkpQs7iu5uFBTpYMXO5CmZQoLUvjiMSlXg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/zz/apk/332223/content/SCREENSHOT/75af47de-e022-4282-a532-03780962bc91.png@webp",
                "https://static.rustore.ru/imgproxy/dRrOi-XMGMWPaH8lXFcYeQjeJqZZu6dYfidYFPaYgJc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/01/apk/332223/content/SCREENSHOT/3e6493e1-f8de-4c16-96a2-2a2c7a8df216.png@webp",
                "https://static.rustore.ru/imgproxy/XTWOMdO9EhnPuoMzFJEst9afvjo6wx9diqNTzBfXrHU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/42/apk/332223/content/SCREENSHOT/d482d1e1-f2fd-4ffb-bb32-774e4db483e5.png@webp",
                "https://static.rustore.ru/imgproxy/U1nJGAQPuYSWMrP_vJH9EDCDTUDPefaO9fdlp6tn9mk/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/5a/apk/332223/content/SCREENSHOT/586d0e96-cfd1-4a50-8170-78c374999ea9.png@webp",
                "https://static.rustore.ru/imgproxy/hj_uKFWSDUInlenWOeZjRqiW89i0GS-B8WRQ2tFz51Q/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/06/apk/332223/content/SCREENSHOT/04988b1d-05df-425b-bbcf-10b57cfa2e2d.png@webp",
                "https://static.rustore.ru/imgproxy/HWaApmCC0B4k5isuT4-rXad49nYl1dLd1SGGvKNs8yA/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/17/75/apk/332223/content/SCREENSHOT/f118f7d7-06f9-4643-b76e-9712d3b5d7e2.png@webp"
            ),
            downloads = 30_000_000
        ),
        AppDetails(
            id = "tanks_blitz",
            name = "Tanks Blitz",
            description = """
                Открывай Tanks Blitz — F2P массовый многопользовательский PVP-шутер. Здесь ты примешь участие в динамичных боях вместе с командой: захватывай базу, зарабатывай очки или уничтожай технику противника[reference:6].
        
                Это бесплатный многопользовательский танковый экшен, где перед игроком стоит очевидная задача: уничтожить команду противников[reference:7].
        
                Игроки могут устраивать командные сражения, приглашать друзей или играть в одиночку, чтобы исследовать виртуальные миры и улучшать свою военную технику[reference:8].
            """.trimIndent(),
            category = "Игры",
            developer = "Lesta Games",
            ageRating = "12+",
            iconUrl = "https://static.rustore.ru/imgproxy/ZWhcUCoxg-3SSdvV0vx4Da0nZjDaPtzKXTMHtd9u9R0/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/5/14/72/apk/2063496338/content/ICON/069d084f-d912-45f8-9bd3-7b79f6e91db8.jpg@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/8Q-70daiXlxERJKq7IUlTacs8grFkbIc3tzxYU3EYiY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/2026/1/19/1b/apk/2063496338/content/SCREENSHOT/3321f386-238c-4d6d-b774-38f7e1cd04a0.jpg@webp",
                "https://static.rustore.ru/imgproxy/X3B72_Xyquh8FYyVnmmR4AwU1tHMO5jTCqJLdaFdBXs/preset:web_scr_lnd_335/plain/https://static.rustore.ru/2026/1/19/5e/apk/2063496338/content/SCREENSHOT/67fd73f6-198c-4ad0-8b72-e88fc62c09c1.jpg@webp",
                "https://static.rustore.ru/imgproxy/u_ZVeUZTDWA9gy-qUeJUqPlPffLxw6Mz9aOVxWYZKvU/preset:web_scr_lnd_335/plain/https://static.rustore.ru/2026/1/19/9e/apk/2063496338/content/SCREENSHOT/ef2ea401-b1bf-4383-b9ca-ea558fd28b49.jpg@webp",
                "https://static.rustore.ru/imgproxy/tRS9qTdnxe_0pUGm-T1dLtzzKyTcloGNvdaIoHVakr4/preset:web_scr_lnd_335/plain/https://static.rustore.ru/2026/1/19/da/apk/2063496338/content/SCREENSHOT/0df8b11a-e899-42a5-8c44-d82a69f80a4c.jpg@webp",
                "https://static.rustore.ru/imgproxy/bv1IZG9dVsKhbCihe-e-fogQolG_q7yoY0YjyhUdfkw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/2026/1/19/b0/apk/2063496338/content/SCREENSHOT/82302151-8783-4733-a4c4-478f11ed1ff4.jpg@webp"
            ),
            downloads = 4_000_000
        ),
        AppDetails(
            id = "roblox_client",
            name = "Roblox",
            description = """
                ROBLOX: ПОТРЯСАЮЩИЕ ИГРЫ ЛЮБЫХ ЖАНРОВ БЕСПЛАТНО
                Широта выбора игр в Roblox не имеет аналогов: кулинарные, симуляторы, гонки, шутеры, казуальные, с выживанием и строительством… Отправляйтесь в грандиозные приключения в РПГ, создавайте империи в тайкунах, участвуйте в спортивных соревнованиях, погружайтесь в фантазийные миры ужастиков и аниме, наслаждайтесь многопользовательскими играми в компании друзей.
                Каждый день в Roblox появляются новые игры от разработчиков со всего мира — знаменитых студий, инди-команд и независимых авторов, которые создают амбициозные проекты во всех жанрах. Здесь всегда есть что-то новое!
                Начните со знакомства с самыми популярными играми на Roblox — например, Forge, Pixel Quest, World Zero, Scary Shawarma, NFL Universe Football, Gunfight Arena или K-Pop Demon Hunters.
                ПОЧЕМУ ВСЕ ЛЮБЯТ ROBLOX
                МНОГОПОЛЬЗОВАТЕЛЬСКИЕ ОНЛАЙН-ИГРЫ
                — Приключения, ролевые игры, симуляторы, полосы препятствий и многое другое
                — Новые интересные игры и плейсы каждый день
                — Многопользовательские битвы, эпические квесты, увлекательные тайкуны
                СОБСТВЕННЫЙ АВАТАР
                — Аватар можно персонализировать, выбрав одежду, аксессуары и прическу
                — На маркетплейсе есть тысячи уникальных предметов, созданных пользователями
                — Каждый может выразить себя с помощью уникальных анимаций и эмоций
                ОБЩЕНИЕ И ИГРЫ С ДРУЗЬЯМИ
                — Можно создавать собственные игровые команды
                — Общение в голосовом и текстовом чате (только после проверки возраста)
                ЛУЧШИЕ В ОТРАСЛИ СТАНДАРТЫ БЕЗОПАСНОСТИ И КУЛЬТУРЫ ОБЩЕНИЯ
                — Расширенные возможности фильтрации и модерации контента
                — Настраиваемый родительский контроль
                — Четкие правила сообщества, поощряющие уважительное общение
                — Круглосуточная служба доверия и безопасности
                СОЗДАТЬ СВОЙ ПЛЕЙС: https://www.roblox.com/develop
                ПОДДЕРЖКА: https://en.help.roblox.com/hc/en-us
                КОНТАКТЫ: https://corp.roblox.com/contact/
                ПОЛИТИКА КОНФИДЕНЦИАЛЬНОСТИ: https://www.roblox.com/info/privacy
                РОДИТЕЛЯМ: https://corp.roblox.com/parents/
                УСЛОВИЯ ИСПОЛЬЗОВАНИЯ: https://en.help.roblox.com/hc/en-us/articles/115004647846
                ВАЖНО. Roblox работает только при наличии подключения к интернету, лучше всего через Wi-Fi.
            """.trimIndent(),
            category = "Игры",
            developer = "Roblox Corporation",
            ageRating = "12+",
            iconUrl = "https://static.rustore.ru/imgproxy/459tHkTbzC1-OJsPjizv5qeUlyGWWfJdFZewf_08mZA/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/6/15/14/apk/2063723267/content/ICON/2c2c3d84-6c43-441d-9fd2-fd7ae36bf27e.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/luwDW90zHM8ULu4wnRZNqoiNb3FfL8Lk3Qatbc6fpVc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/15/f7/apk/2063723267/content/SCREENSHOT/9ca23ed9-df42-4b28-828b-d67c75bdc398.jpeg@webp",
                "https://static.rustore.ru/imgproxy/TmJPX0f7O8Cpq1kFTQxixaHzbet8hOT7Ec0DgbrOnX0/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/15/6f/apk/2063723267/content/SCREENSHOT/30775a5d-e0f7-4ccd-be3d-a23062100159.jpeg@webp",
                "https://static.rustore.ru/imgproxy/NecowGhlOtglSUn2iPP81R8dhOFy_5XTkL-gbc-vUK4/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/15/40/apk/2063723267/content/SCREENSHOT/888f7110-badd-46fe-aa81-c67205e1e6cd.jpeg@webp",
                "https://static.rustore.ru/imgproxy/CFXHkbT7TPU_27wvtd8xONxpX7Y_Gj-0b1obN9ULQL8/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/15/75/apk/2063723267/content/SCREENSHOT/f6ff965e-1107-48a4-bb9d-258d38c8ec40.jpeg@webp",
                "https://static.rustore.ru/imgproxy/RsWtOPMdscLZiLCkjK9W3IAUP134kBE9OkXRDWa3xnw/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/15/b4/apk/2063723267/content/SCREENSHOT/e7e1c409-50d2-4f25-b5f7-8f705dd122d2.jpeg@webp"
            ),
            downloads = 700_000
        ),
        AppDetails(
            id = "mobile-legends",
            name = "Mobile Legends: Bang Bang",
            description = """
                Установите игру прямо сейчас и получите шанс выиграть набор ресурсов или новый облик героя! Количество призов строго ограничено, поэтому не пропустите уведомление с промокодом. Акция действует до 30 июня 2026 года или до полного исчерпания всех наград.

                Mobile Legends: Bang Bang — это динамичная MOBA-игра для Android, где реальные игроки сражаются в командах по 5 человек, используя любимых героев. После установки вы оцените невероятно быструю систему подбора соперников и захватывающие бои, где можно продемонстрировать свои навыки. Это именно та многопользовательская арена, которую вы так долго искали.

                Игровой процесс соответствует классическим стандартам MOBA: бои в реальном времени на трех линиях. Ваша цель — победить, уничтожая врагов и захватывая их башни. Вы сразитесь с игроками со всего мира на аренах с двумя дикими боссами и четырьмя районами джунглей. В игре огромная коллекция героев на любой вкус: убийцы, стрелки, танки и герои поддержки. У каждого уникальные характеристики, сильные и слабые стороны, а также особый внешний вид. Успех зависит от стратегии и командной работы: иногда мудрее будет лечить союзников или блокировать атаки, вместо того чтобы атаковать врага.

                Особое внимание привлекает система подбора игроков. Здесь нет места для Pay to Win, так как репутация и статистика героев зависят исключительно от ваших навыков. Вы найдете честную и безопасную среду с сбалансированными сражениями, что делает соревнование сплошным удовольствием. Система подбора работает молниеносно, поэтому вы сразу попадете на поле боя с подходящими противниками без задержек. Вам не придется тратить время на меню или экраны ожидания: взяли телефон в руки — и через секунды начинается битва. Это идеальный вариант для тех, кто ценит глубокую, но казуальную игру и не имеет много свободного времени.

                Управление в Mobile Legends интуитивно понятно и соответствует отраслевым стандартам. Левый джойстик управляет движением героя, а правый — действиями и навыками. Игра внедряет инновации, такие как экипировка одним касанием, что позволяет не покидать экран битвы и не лазать по меню для снаряжения.

                Особая новинка решает проблему разрывов связи. В игре есть сверхбыстрая система повторного подключения: вы вернетесь в бой за считанные секунды после восстановления интернета. Кроме того, если игрок выпадает из матча, его герой не останется без присмотра благодаря умным стратегиям, которые автоматически управляют персонажем. Это гарантирует, что вы никогда не окажетесь в ситуации 5 на 4.

                Mobile Legends: Bang Bang доказывает, что MOBA-игры могут быть отлично адаптированы для мобильных устройств. Благодаря потрясающей графике, увлекательному геймплею и уникальным функциям, это игра, которая не оставит вас равнодушными.

                Скачайте Mobile Legends: Bang Bang сегодня и начните свой путь к победе!
                """.trimIndent(),
            category = "Игры",
            developer = "Youngjoy Technology Limited",
            ageRating = "12+",
            iconUrl = "https://static.rustore.ru/imgproxy/2Zs8W6_KwSnXewM-A8xhLj6_v3Elq10qYV2OvlGc_3w/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/6/17/7f/apk/2063570005/content/ICON/4143e976-8bd8-46df-b5b7-618096272677.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/sNKbGSrmO0h1fSow7TsgwTOCw--H39abts6ZRl2WhRM/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/2063570005/content/SCREENSHOT/6c109c51-839e-446d-9a53-b06d8db03ecc.png@webp",
                "https://static.rustore.ru/imgproxy/pf7OFgxZkbu6R0kSjAIo5ws9i49DXSP6cSV1jqppLLk/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/2063570005/content/SCREENSHOT/67bb1777-1d10-4f72-b433-0e6741caaed7.png@webp",
                "https://static.rustore.ru/imgproxy/ndW_58Pq8Gq5LdB9sA06tjun0Vr-XD4N4qu1ocwYZQQ/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/2063570005/content/SCREENSHOT/49353608-6804-4c57-a40d-439b03108573.png@webp",
                "https://static.rustore.ru/imgproxy/wY2ey-rEUyHoIfLOV3E_7_bzIY6nW6X3IadoggXgvoY/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/2063570005/content/SCREENSHOT/941c6f13-9d2b-4631-b326-f1d834e0e069.png@webp"
            ),
            downloads = 3_000_000
        ),
        AppDetails(
            id = "rostel",
            name = "Госуслуги",
            description = """
                Приложение «Госуслуги» — ваш помощник для взаимодействия с ведомствами и государством

                В приложении можно оплачивать штрафы и госпошлины, подавать заявления в ведомства, хранить личные документы и предъявлять их в бытовых ситуациях, сканировать товары, управлять согласиями на использование личных и биометрических данных и многое другое
                """.trimIndent(),
            category = "Государственные",
            developer = "Министерство цифрового развития, связи и массовых коммуникаций Российской Федерации",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/6N0m3heN9vooK5mQ2eqkSYr32Lux3QSOPiMh14YqM30/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/2/19/d7/apk/537791/content/ICON/b2916730-6a34-4ebe-aedf-df926e807741.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/ISSrYdwu348Ip49878E52ozKRYIstKALAVZag4-nxkc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/11/25/ac/apk/537791/content/SCREENSHOT/a8b8dd05-fc73-4cbb-8ae5-7d9ea578db2f.png@webp",
                "https://static.rustore.ru/imgproxy/1fDCUgA0culNtavp0LbRNVKXwThh2myQLMnSyITuKjU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/11/25/0d/apk/537791/content/SCREENSHOT/a22d257d-e361-48f3-b0db-04d42a12e88b.png@webp",
                "https://static.rustore.ru/imgproxy/YXRFMrbrKbm18xqTgjvhyX7o3eVFK7YGsl0R8MTKSpQ/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/11/25/25/apk/537791/content/SCREENSHOT/eca9e98f-b5ae-4a7e-b28a-85a1d1e3987b.png@webp",
                "https://static.rustore.ru/imgproxy/stMiZ-VPdq9XZvKZQBhR227-pCo1NQjblesNVyWtbUg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/11/25/41/apk/537791/content/SCREENSHOT/da47acce-30a0-4c34-9e06-0f8ddb302380.png@webp",
                "https://static.rustore.ru/imgproxy/2_MLUdlbOOldexKQYE37CGSnENGBSfHhnqKT4ZdJ7WY/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/11/25/e2/apk/537791/content/SCREENSHOT/fb843364-8a62-4b6d-9fe0-cb218bfd7bea.png@webp"
            ),
            downloads = 40_000_000
        ),
        AppDetails(
            id = "amina-app",
            name = "Амина",
            description = """
                «Амина» – официальное мобильное приложение для иностранных граждан, которые приезжают в Москву и Московскую область в безвизовом порядке.
                """.trimIndent(),
            category = "Государственные",
            developer = "Информационный город ГКУ",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/XQF9JvVyG9z2-zEbYTRjCJ8B0mtpkaq_exCfQ_Y9ins/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2063638092/content/ICON/68825636-2aaa-4836-8af3-e01fb9a7c798.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/NjveSeQz5XtozBnpF8Y8rgRUPznvhZKSc6lvOgVTyV8/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/5/5d/apk/2063638092/content/SCREENSHOT/90f3809d-cdf8-47b6-b246-6b1d0ae565ce.jpg@webp",
                "https://static.rustore.ru/imgproxy/Smr2BiOsW3wHbdw5KfC7JUCG3qTXr1eWFaIVFGvrWX0/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/5/d7/apk/2063638092/content/SCREENSHOT/bbe8ad19-445b-449b-8e2e-0181145e4d1a.jpg@webp",
                "https://static.rustore.ru/imgproxy/4EQU0wSw0XL57OZ7VwKDOQGI6tdN7oMDRALPnebWIGs/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/5/90/apk/2063638092/content/SCREENSHOT/80376489-81ee-4664-890d-455dd9576d0f.jpg@webp"
            ),
            downloads = 4_000_000
        ),
        AppDetails(
            id = "dublgis-dgismobile",
            name = "2ГИС: навигатор, транспорт, друзья на карте",
            description = """
                Карте бензина в 2ГИС — узнайте, работает ли заправка, насколько она загружена, есть ли сейчас топливо и ограничения на продажу. Данные обновляем каждые 10 минут.

                Карта, навигатор, общественный транспорт, путеводитель, справочник, а также локатор для отслеживания местоположения близких на карте в одном приложении. 2ГИС покажет ваше местоположение, найдёт нужный адрес, поможет спланировать маршрут на машине, автобусе, велосипеде или пешеходный. А ещё друзья прямо на карте!

                Приложение бесплатное, работает онлайн или офлайн: используйте карты и навигатор без интернета, скачав нужный город.

                Навигация с поддержкой Android Auto. Приготовьтесь увидеть точно отрисованные дороги с тоннелями и многоуровневыми развязками в 3D. 2ГИС построит оптимальный маршрут с учётом пробок, ДТП и ремонтных работ. Покажет вашу скорость, избавляя от необходимости часто поглядывать на спидометр. Работает как антирадар, предупредит о камерах скорости и других типах. Покажет ближайшие парковки. Поддерживается режим «Картинка в картинке», так ваш маршрут остаётся на экране даже при свёрнутом приложении.

                Приложение комбинирует gps-навигацию с другими технологиями, чтобы движение по маршруту было точным, даже в центре Москвы и при междугородних поездках. Включите 2ГИС в Android Auto — это бесплатно.

                Навигатор для пешеходов, велосипедов и самокатов с точным GPS и ГЛОНАСС. 2ГИС умеет строить маршруты по городу с учётом уклона и набора высоты, велодорожек и лестниц.

                Голоса звёзд. Навигатор сопровождает вас голосами Николая Дроздова, комика Чебаткова, актёра Петра Гланца и других героев.

                Общественный транспорт. Навигатор построит маршруты на метро, автобусе, маршрутке, троллейбусе, трамвае и электричке. Выбирайте кратчайшие или самые удобные. Включите отслеживание автобусов на карте в реальном времени. Ориентируйтесь на расписание, — включая расписание электричек, — и смотрите на реалистичный прогноз прибытия автобуса к остановке.

                Друзья на карте. Добавляйте близких «в друзья» — чтобы видеть геолокацию друг друга на карте городе в реальном времени на карте! Можно не спрашивать «где ты?», — а просто посмотреть местоположение друзей на карте.

                Вы также можете отправлять любому человеку ссылку на свою геопозицию или маршрут, по которому едете. Управляйте видимостью своего местоположения. Это удобно, приватно и помогает всегда оставаться на связи.

                Функция может служить как семейный локатор: добавьте «в друзья» своего ребёнка или бабушек и дедушек, чтобы не беспокоиться, если они не отвечают в мессенджере. И получить автоматический пуш, когда родственник дома.

                Карты на максималках. C реалистичными моделями зданий, дорогами, остановками — на карте обозначены даже деревья в парке и входы в здания! Для торговых центров, вокзалов, аэропортов и других крупных объектов есть подробные поэтажные планы зданий.

                Всё о городе. В поиске вы найдёте контакты компаний и организаций, адреса, время работы, меню ресторанов, информацию о товарах и услугах и даже цены с фотографиями. Данные обновляются регулярно.

                Отзывы. Помогают не только находить компании, товары и услуги, но и выбирать лучшие! В приложении собраны десятки тысяч отзывов, реальные фото от клиентов и показан честный рейтинг места.

                Навигатор для грузовиков построить маршруты с учётом индивидуальных особенностей транспортных средств и грузов.

                Приложение-компаньон 2ГИС. Уведомления для умных часов на Wear OS. Помощник на маршрутах пешком, на велосипеде или на общественном транспорте, построенных в основном приложении 2ГИС: карта, подсказки маневров, вибро-оповещения о приближающемся повороте или нужной остановке. Компаньон запускается на часах автоматически, когда вы запускаете навигатор в телефоне. Доступно для Wear OS версии 3.0 и выше.

                Офлайн карты, навигация, общественный транспорт и gps-трекер в одном приложении.
            """.trimIndent(),
            category = "Транспорт",
            developer = "ООО \"ДубльГИС\"",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/OYKFMgpckkNGCd1jKcnHKojgnsMJ09qjfr9TYyjkyx4/preset:web_app_icon_160/plain/https://static.rustore.ru/2026/2/2/1e/apk/260799/content/ICON/8e439c58-11cf-4e0a-a3dc-9596eda08cfd.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/uhyBIWGLeCVcgXOxBw9VjldATvp_QLBEnUS3DYm6SDU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/49/apk/260799/content/SCREENSHOT/33e4bbb5-8860-4fc7-a10d-599c0377ce25.jpg@webp",
                "https://static.rustore.ru/imgproxy/8OYrEhFqiAS2sMJJ5g7fkttb_G0A4Z-HCAPx4FSgTcg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/a5/apk/260799/content/SCREENSHOT/96686015-4174-4278-8869-cdacfbc3ea4c.jpg@webp",
                "https://static.rustore.ru/imgproxy/vjP7VSuM153pNkwdiO1CZO6p-wbvciyCL8eB-s0Uc7o/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/90/apk/260799/content/SCREENSHOT/705494a3-5c9a-483f-945b-e3dadd4fde76.jpg@webp",
                "https://static.rustore.ru/imgproxy/Xu7-Ehui39p5f8SV_18JgwJwptP-NYR0WEtqgbEH0cY/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/ec/apk/260799/content/SCREENSHOT/c78c325a-1dc0-42d9-af4a-5a36ba9a02ba.jpg@webp",
                "https://static.rustore.ru/imgproxy/ndPKk_duPynEWhvDFypsUY8JulCmaDi_NcD0PADXCc4/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/0c/apk/260799/content/SCREENSHOT/e227f1fd-e27b-4e35-ae1f-c38dc8fc9218.jpg@webp",
                "https://static.rustore.ru/imgproxy/IbXNNvXPX76D03eQSUvv-OBuwAinOzYHjmfC33tiByI/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/16/5c/apk/260799/content/SCREENSHOT/47348a78-8052-4b6a-89b2-74760f623674.jpg@webp"
            ),
            downloads = 30_000_000
        ),
        AppDetails(
            id = "yandex-yandexnavi",
            name = "Яндекс Навигатор",
            description = """
                Стройте маршруты с учётом параметров автомобиля
                Если вы на грузовике — Навигатор поможет не попасть туда, где нельзя ехать. Он умеет учитывать ограничения: грузовой каркас (в Москве), фактическую массу, РММ грузоподъёмность, нагрузку на ось, движение с прицепом, высоту, ширину, длину и
                эко-класс. Вам нужно лишь активировать «Маршруты для грузовиков» и указать там параметры автомобиля.

                Выбирайте оптимальные маршруты
                Навигатор прокладывает маршруты с учётом пробок, аварий и ремонтных работ. Он предлагает до трёх вариантов проезда и для каждого подсчитывает время в пути. Если маршрут проходит по платному участку, приложение предупредит и об этом.

                Прокладывайте маршруты офлайн
                Строить маршруты можно не только онлайн, но и офлайн. Навигация без интернета будет доступна, если вы заранее скачаете карту города или региона в настройках приложения. Искать организации также можно будет офлайн.

                Получайте важную информацию в пути
                Когда вы в дороге, на экране показывается дистанция, которую нужно преодолеть, а также оставшееся время. Есть голосовое ведение по маршруту и подсказки на экране: Навигатор рассказывает о направлении движения, камерах контроля скорости и событиях на маршруте голосом, а также обозначает их на карте. Если во время движения ситуация с пробками меняется и приложение находит более быстрый маршрут, оно сообщает об этом водителю.

                Соблюдайте скоростной режим
                Навигатор знает о скоростных лимитах на разных дорожных участках. Если вы едете слишком быстро, он предупредит о превышении скорости звуковым сигналом.

                Управляйте голосом с помощью Алисы
                За голосовое управление в Навигаторе отвечает Алиса — к ней вы можете обратиться по любому поводу. Скажите «Слушай, Алиса» или нажмите кнопку с микрофоном в левой части экрана — и она выйдет на связь. Например: «Слушай, Алиса» — «Поехали домой» или «Построй маршрут до аэропорта Домодедово». Таким же способом вы можете сообщать Навигатору о дорожных событиях («Здесь авария») — чтобы он отметил их на карте.
                Алисе можно задавать самые разные вопросы. Произнесите «Какой штраф за превышение скорости?» или «67 — это какой регион?» — и вы сразу узнаете ответ.
                Алиса охотно играет с детьми и со взрослыми в города и слова, загадывает актёров и даже гадает. Скажите ей «Давай поиграем» и выберите, что хотите делать.

                Ориентируйтесь на местности
                В приложении подробная карта, которая постоянно обновляется. На ней обозначены рестораны, кафе, бары, магазины, заправки, аптеки, стадионы, юридические компании и другие организации. Если по пути вы, например, захотите поужинать, можно просто сказать «Слушай, Яндекс, где поесть рядом?» Приложение зафиксирует ваше местоположение и предложит подходящие варианты. Карта поможет сориентироваться не только в городе, но и за его пределами.

                Сохраняйте историю
                Навигатор помнит историю пунктов назначения. Можно, например, ввести адрес и прикинуть маршрут вечером, а наутро просто выбрать цель поездки из списка. История и избранное сохраняются в облаке и доступны на всех ваших устройствах, чтобы не теряться.

                Найдите место на парковке
                Приложение знает обо всех парковках Москвы, расположенных в пределах Третьего транспортного кольца. На карте сразу видно, где можно оставлять машину, а где парковка запрещена. В других районах столицы на карте также отмечены некоторые городские парковки.

                Информация по крупным парковкам доступна также в Санкт-Петербурге, Киеве, Минске, Краснодаре, Екатеринбурге, Нижнем Новгороде, Казани, Ростове-на-Дону и других городах.

                Возьмите в путешествие
                Яндекс Навигатор показывает карты дорог и строит маршруты в России, Абхазии, Азербайджане, Армении, Беларуси, Грузии, Казахстане, Кыргызстане, Молдове, Таджикистане, Турции, Узбекистане и Украине.

                Приложение предлагает включить виджет с поиском Яндекса для панели уведомлений.

                * Продолжительное использование GPS в фоне может значительно сократить срок службы батареи.
            """.trimIndent(),
            category = "Транспорт",
            developer = "ООО \"ЯНДЕКС\"",
            ageRating = "0+",
            iconUrl = "https://static.rustore.ru/imgproxy/FvKuW-aUKk34jUz1ZEPXebdfDR0ikU93-JJYC5_Oh4Y/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/595135/content/ICON/32cb5e63-9c59-4280-9a6a-c808113be88f.png@webp",
            screenshots = listOf(
                "https://static.rustore.ru/imgproxy/HylmPhV1JLvgrt1C5sMSE1XbGGCuX3GE7zOZBsHwSzE/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/20/2c/apk/595135/content/SCREENSHOT/94d172a2-f68e-439f-938b-0a678ff9ce87.jpg@webp",
                "https://static.rustore.ru/imgproxy/TuFlE7hAOPMeGzjY1frqFKZk6BVzh9BnYkrSu2dCWKU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/4/02/apk/595135/content/SCREENSHOT/04755902-cae9-48ce-8751-cac80f225be0.jpg@webp",
                "https://static.rustore.ru/imgproxy/-Gv7JR1eyQTnt21boIUpcoz1bVfF0ndJl5OSNoxuCFU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/4/09/apk/595135/content/SCREENSHOT/d04c92a8-9218-4f2b-b26f-df73a7160e61.jpg@webp",
                "https://static.rustore.ru/imgproxy/MR9_J7tMNtprFrwsq_-5xnQJI6CLnuuvnoulMoPNktg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/4/07/apk/595135/content/SCREENSHOT/a905099a-3b57-49de-8dd4-ec398733a346.jpg@webp",
                "https://static.rustore.ru/imgproxy/LHlyqvyga-gnl-yd-5qUoWADME4ywUz1mgdvRmy1aE4/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/6/4/0e/apk/595135/content/SCREENSHOT/6bdaf4ad-7c4c-4f2e-8293-09e16ac0dbf6.jpg@webp"
            ),
            downloads = 18_000_000
        ),

    )
    private val mockAppSummaries = mockApps.map { appDetails ->
        AppSummary(
            appDetails.id,
            appDetails.name,
            appDetails.description.substring(0, 100).plus("..."),
            appDetails.category,
            appDetails.iconUrl,
            appDetails.downloads) }

    private val categories = mockApps
        .groupBy { it.category }
        .map { (name, apps) -> Category(name, apps.size) }
}