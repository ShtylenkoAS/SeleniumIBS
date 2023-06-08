@all
@severity=minor
@issue=testIssue
Feature: Выполнение итогового задания

  @1
  Scenario Outline: Сценарий - Проверка магазина
    * Нажимаем на кнопку 'Каталог'
    * Выбираем категорию: "<catalogMenu>"
    * Выбираем подкатегорию: "<subMenu>"
    * Выбираем минимальную цену: "<minPrice>"
    * Прожимаем чекбокс: "<companyName>"
    * Проверяем, что паджинация страницы = 24
    * Сохраняем наименование первого товара в списке
    * Вводим в поисковую строку наименование первого товара в списке
    * Проверяем, что в поисковой выдаче не более 1 товара
    * Проверяем, что наименование товара соответствует сохраненному значению

    Examples:
      | catalogMenu          | subMenu    | minPrice | companyName |
      | Комплектующие для ПК | Видеокарты | 20000    | Gigabyte    |
      | Периферия            | Клавиатуры | 2000     | A4Tech      |
      | Периферия            | Мыши       | 100      | A4Tech      |
      | Накопители данных    | USB Flash  | 3000     | Samsung     |