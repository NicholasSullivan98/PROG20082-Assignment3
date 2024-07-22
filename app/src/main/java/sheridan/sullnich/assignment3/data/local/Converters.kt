package sheridan.sullnich.assignment3.data.local

import androidx.room.TypeConverter
import sheridan.sullnich.assignment3.domain.Category
import java.util.*

class Converters {
    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromConditionToInt(category: Category): Int{
        return category.ordinal
    }

    @TypeConverter
    fun fromIntToCondition(code: Int): Category {
        return Category.entries[code]
    }

}