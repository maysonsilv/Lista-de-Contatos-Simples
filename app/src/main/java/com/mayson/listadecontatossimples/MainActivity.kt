package com.mayson.listadecontatossimples

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mayson.listadecontatossimples.DetailActivity.Companion.EXTRA_CONTACT


class MainActivity : AppCompatActivity(), ClickItemContactListener {
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer)

        initDrawer()
        fetchListContact()
        bindViews()


    }

    private fun fetchListContact(){
        var list = arrayListOf(
                Contact(
                    "Meyson Silva",
                    "(00) 0000-0000",
                    "img.png"

                ),

                Contact(
                    "Fulano da Silva",
                    "(11) 1111-1111",
                    "img.jpg"
                ),

                Contact(
                    "Cybele",
                    "(98) 98483-4018",
                    "cy.img"
                ),

                Contact(
                    "Pai",
                    "(99) 99177-0775",
                    "imagen.png",

                    ),
                Contact(
                    "Mãe",
                    "(98) 99891-0909",
                    "foto.png"
                )

        )

        getInstanceSharedPreferences().edit{
            val json = Gson().toJson(list)
            putString("contacts", json)
            commit()
        }



        }

    private fun getInstanceSharedPreferences(): SharedPreferences {
        return getSharedPreferences("com.mayson.listadecontatossimples.PREFERENCES", Context.MODE_PRIVATE)
    }




    private fun initDrawer() {
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }


    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact> {

        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnType)
            
        }


    private fun updateList(){
        val list = getListContacts()
        adapter.updateList(list)

    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu_1 -> {
                showToast("Exibindo item de menu 1")
                return true

            }
            R.id.item_menu_2 -> {
                showToast("Exibindo item de menu 2")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}