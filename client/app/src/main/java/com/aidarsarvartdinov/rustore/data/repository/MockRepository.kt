package com.aidarsarvartdinov.rustore.data.repository

import com.aidarsarvartdinov.rustore.data.models.ApiResult
import com.aidarsarvartdinov.rustore.data.models.AppDetails
import com.aidarsarvartdinov.rustore.data.models.AppSummary
import com.aidarsarvartdinov.rustore.data.models.Category

class MockRepository: AppRepository {
    override suspend fun getApps(): ApiResult<List<AppSummary>> {
        return ApiResult.Success(apps.map { appDetails ->
            AppSummary(
                appDetails.id,
                appDetails.name,
                appDetails.description.substring(20),
                appDetails.category,
                appDetails.iconUrl) })
    }

    override suspend fun getAppsByCategory(categoryName: String): ApiResult<List<AppSummary>> {
        return ApiResult.Success(apps
            .filter { appDetails -> appDetails.category == categoryName }
            .map { appDetails ->1
                    AppSummary(
                        appDetails.id,
                        appDetails.name,
                        appDetails.description.substring(20),
                        appDetails.category,
                        appDetails.iconUrl) })
    }

    override suspend fun getAppDetails(appId: String): ApiResult<AppDetails> {
        val app: AppDetails? = apps.find { appDetails -> appDetails.id == appId }
        if (app == null) {
            return ApiResult.Error("App not found")
        }

        return ApiResult.Success(app)
    }

    override suspend fun getCategories(): ApiResult<List<Category>> {
        return ApiResult.Success(listOf(
            Category(
                "Финансы", 2
            ),
            Category(
                "Инструменты", 1
            ),
            Category(
                "Игры", 1
            ),
            Category(
                "Государственные", 1
            ),
            Category(
                "Транспорт", 1
            ),
        ))
    }

    private companion object MockData {
        val apps = listOf(
            AppDetails(
                id = "1",
                name = "Мой Банк",
                description = "Управляйте своими финансами легко и безопасно. Переводы, платежи, кредиты, вклады и инвестиции в одном приложении. Круглосуточная поддержка и биометрия для входа.",
                category = "Финансы",
                developer = "ООО «МойБанк Тех»",
                ageRating = "18+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            ),
            AppDetails(
                id = "2",
                name = "Дом Калькулятор",
                description = "Идеальный инструмент для строителей и ремонтников. Калькулятор материалов, смета, конвертер единиц, угломер и уровень в одном приложении. Без рекламы!",
                category = "Инструменты",
                developer = "ООО «СтройЦифра»",
                ageRating = "6+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            ),
            AppDetails(
                id = "3",
                name = "Гонки на выживание",
                description = "Экстремальные гонки на постапокалиптических трассах. Собирайте оружие, улучшайте машину, уничтожайте противников и доберитесь до финиша первым. Онлайн-режим до 20 игроков.",
                category = "Игры",
                developer = "GameCraft Studio",
                ageRating = "16+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            ),
            AppDetails(
                id = "4",
                name = "Госуслуги Онлайн",
                description = "Получайте государственные и муниципальные услуги в электронном виде. Запись к врачу, оплата штрафов, подача заявлений, проверка налогов — всё в одном приложении. Поддерживается вход через ЕСИА.",
                category = "Государственные",
                developer = "Минцифры РФ",
                ageRating = "0+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            ),
            AppDetails(
                id = "5",
                name = "Яндекс.Карты",
                description = "Навигация с пробками, общественный транспорт, маршруты на автомобиле и пешком. Более 2000 городов мира, офлайн-карты, информация о заведениях и отзывы.",
                category = "Транспорт",
                developer = "ООО «Яндекс»",
                ageRating = "12+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            ),
            AppDetails(
                id = "6",
                name = "Финансовый помощник",
                description = "Автоматический учёт доходов и расходов, бюджетирование, интеллектуальный анализ трат, инвестиционные рекомендации. Подключение к банкам через Open API.",
                category = "Финансы",
                developer = "FinTech Solutions",
                ageRating = "18+",
                iconUrl = "https://wplife.ru/placeholder/200x200",
                screenshots = listOf(
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200",
                    "https://wplife.ru/placeholder/600x1200"
                )
            )
        )
    }
}