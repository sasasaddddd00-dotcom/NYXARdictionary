package com.nyxar.dictionary.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "words")
data class Word(@PrimaryKey(autoGenerate = true) val id: Long = 0, val nyxar: String, val root: String, val pos: String, val japanese: String, val detail: String, var isBookmarked: Boolean = false)

@Dao
interface NyxarDao {
    @Query("SELECT * FROM words WHERE nyxar LIKE :q OR japanese LIKE :q LIMIT 100") fun search(q: String): Flow<List<Word>>
    @Query("SELECT * FROM words WHERE id = :id") suspend fun getById(id: Long): Word
    @Update suspend fun update(word: Word)
}

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class NyxarDatabase : RoomDatabase() {
    abstract fun dao(): NyxarDao
    companion {
        fun get(ctx: android.content.Context) = Room.databaseBuilder(ctx.applicationContext, NyxarDatabase::class.java, "nyxar.db")
            .createFromAsset("databases/nyxar.db")
            .build()
    }
}