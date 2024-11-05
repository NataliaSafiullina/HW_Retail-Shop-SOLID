# Магазин

## Возможности программы:

1. Выход
2. Каталог товаров
3. Выбрать товары
4. Показать корзину
5. Оплатить счет
6. Узнать баланс

### Не использовать "магические числа"
Все числа вынесены в константы. 
В итерациях по спискам в качестве границ используется размер списка.
Пример констант, количество строк при постраничном выводе в консоль:

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/ConsoleUI.java#L14

### DRY
Вывод информации на экран, будь то меню, или список товаров, или список возможных способов оплаты,
осуществляет один и тот же метод с настройками через его параметры.

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/ConsoleUI.java#L17C5-L37C6

## SOLID:

### Single-responsibility principle (единственная ответственность): 

Каждый класс выполняет только те функции, за которые он ответственный.
Клас Products описывает продукты, класс Prices отвечает за цены, 
класс ShoppingCart описывает корзину покупателя.

Класс Purchase отвечает только за покупки: наполнить корзину, показать корзину,
посчитать корзину, очистить корзину. Вот пример:

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/buyer/Purchase.java#L14-L57


### Open-closed principle (открыты для расширения, но закрыты для модификации): 

Семейство классов: Wallet (абстрактный класс) и его дети Card и Bonus, готовы к расширению, 
можно легко добавить новый способ оплаты. 

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/money/Wallet.java#L3-L28

Семейство связка интерфейса UserInterface и класса ConsoleUI могут быть расширены другим
пользовательским интерфейсом, например голосовым.

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/UserInterface.java#L5-L10

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/ConsoleUI.java#L11


### Liskov substitution principle (роль за предка): 

Дети класса Wallet могут выполнять функцию "кошелька", т.е. своего родителя. 
Можно задать баланс и название для объекта дочернего класса.

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/money/Card.java#L8-L11


### Interface segregation principle (отдельный интерфейс для отдельной функциональности): 

Карту денег (класс Card) и карту бонусов (класс Bonus) можно пополнить.
Но вывести средства можно только с карты с деньгами.
Для этих разных возможностей созданы два отдельных интерфейса:
- TopUp - пополнить
- WithdrawMoney - вывести

Класс Card реализует оба интерфейса. А класс Bonus только TopUp.

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/money/Card.java#L8

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/money/Bonus.java#L8


### Dependency inversion principle (зависеть от абстракций, а не от конкретных реализаций):

Списание средств на кассе (класс CashDesk) не зависит от того рассчитываемся
ли мы Картой (Card) или бонусами (Bonus), метод класса работает с абстракцией Wallet.

https://github.com/NataliaSafiullina/HW_Retail-Shop-SOLID/blob/9754ce0ca66797f05f4b8a2ff1028df50f53789e/src/main/java/ru/safiullina/shop/CashDesk.java#L7

