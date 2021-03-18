package entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")

class Notaent (
    // Int? = null so when creating instance id has not to be passed as argument
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    @ColumnInfo (name = "tituto") val titulo: String,
    @ColumnInfo (name = "texto") val texto: String,
    @ColumnInfo (name = "data") val data: String

)


