import java.util.*

fun main() {
    print("Введите выражение(Допустимы только бинарные операторы: +, -, *, /. В качестве операндов беззнаковые числа. Разделитель \"пробел\".): ")
    print(translate(read()))
}


fun read(): List<String>
{
    var str = readLine()
    if (str.isNullOrBlank())
    {
        print("Вы ничего не ввели!")
    }
    var list = str!!.trim().split(" ")
    for (elem in list)
    {
        if (!isNum(elem))
            for (ch in elem)
                if (ch == '+' || ch == '-' || ch == '/' || ch == '*')
                    continue
                else
                    throw Throwable("Выражение содержит недопустимый символ!")
    }
    return list
}

fun translate(inputParts: List<String>): String
{
    var operators = listOf<String>("+", "-", "/", "*")
    val res = Stack<String>()
    for (elem in inputParts.reversed())
    {
        if (isNum(elem))
        {
            res.push(elem);
        }
        else
        {
            for (ch in operators)
                if (elem.contains(ch))
                    if (res.size > 1)
                        res.push("(${res.pop()} $ch ${res.pop()})")
                    else
                        throw Throwable("Недостаточно чисел в выражении!")
        }
    }
    if (res.size > 1)
        throw Throwable("Слишком много чисел в выражении!")
    return res.pop()
}


fun isNum(st: String): Boolean
{
    for (elem in st)
    {
        if (!elem.isDigit())
            return false
    }
    return true
}