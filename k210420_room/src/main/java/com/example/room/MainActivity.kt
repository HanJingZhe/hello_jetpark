package com.example.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.room.database.AppDatabase
import com.example.room.entity.Book
import com.example.room.entity.User
import com.qtgm.base.utils.MyLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val bookDao = AppDatabase.getDatabase(this).bookDao()

        val user1 = User("苹", "果guo", 40)
        val user2 = User("香", "焦", 63)

        btnGetUsers.setOnClickListener {
            thread {
                val users = userDao.loadAllUsers()
                MyLog.d("************select*************")
                users.forEach {
                    MyLog.d(it.toString())
                }
                MyLog.d("************select*************\n")
            }
        }

        btnAddUser.setOnClickListener {
            thread {
                userDao.insertUser(user1)
                userDao.insertUser(user2)
            }
        }

        btnUpdateUser.setOnClickListener {
            thread {
                val newUser = User("苹", "guoguo", 50)
                userDao.updateUser(newUser)
            }
        }

        btnDeleteUser.setOnClickListener {
            thread {
                val deleteUser = userDao.deleteUser(user1)
                MyLog.d(deleteUser.toString())
            }
        }

        btnDeleteUserByName.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        btnGetUserByAge.setOnClickListener {
            thread {
                val loadUsersOlderThan = userDao.loadUsersOlderThan(55)
                MyLog.d(loadUsersOlderThan.toString())
            }
        }


        btnQueryAllBook.setOnClickListener {
            thread {
                val books = bookDao.selectAllBook()
                MyLog.d("\n************select*************")
                books.forEach {
                    MyLog.d(it.toString())
                }
                MyLog.d("************select*************\n")
            }
        }

        btnAddBook.setOnClickListener {
            thread {
                var book = Book("西游记", 500,"莫失")
                val insert = bookDao.insert(book)
                MyLog.d(insert.toString())
            }
        }

    }


}