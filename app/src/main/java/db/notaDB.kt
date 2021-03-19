package db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dao.Notadao
import entities.Notaent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Notaent::class), version = 1, exportSchema = false)
public abstract class notaDB : RoomDatabase() {

    abstract fun Notadao(): Notadao

    private class NotaDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var Notadao = database.Notadao()

                    //Delete all content here.
                    //Notadao.deleteAll()

                    //add sample words
                    //var notaent = Notaent(1,"Rua Cortada","Não conseguimos passar","18/03/2021")
                    //Notadao.insert(notaent)
                    //notaent = Notaent(2,"Sem Semáforos","Estrada sem semáforos","18/03/2021")
                    //Notadao.insert(notaent)

                }
            }
        }

    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: notaDB? = null


        //função principal que permite criar/obter a db
        fun getDatabase(context: Context, scope: CoroutineScope): notaDB {
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }

            synchronized(this){
                //gerar a dbSQLite
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    notaDB::class.java,
                    "notas_database",
                )

                    //estratégia de destruição
                    //.fallbackToDestructiveMigration()
                    .addCallback(NotaDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance


            }

        }
    }
}
