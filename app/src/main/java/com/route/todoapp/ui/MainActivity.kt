package com.route.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.route.todoapp.R
import com.route.todoapp.databinding.ActivityMainBinding
import com.route.todoapp.ui.fragment.add.AddBottomSheetFragment
import com.route.todoapp.ui.fragment.list.ListFragment
import com.route.todoapp.ui.fragment.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var dataBinding : ActivityMainBinding
    val listFragment : ListFragment = ListFragment()
    val settingsFragment : SettingsFragment = SettingsFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this , R.layout.activity_main)
        showFragment(listFragment)
        showBottomNavigationItem()
        showBottomSheet()
//        listFragment.onItemClickedToBeUpdated = object : ListFragment.OnItemClicked{
//            override fun onItemClickedToBeUpdated(position: Int, todo: Todo) {
//                val intent  = Intent(this@MainActivity , UpdateTodoActivity::class.java )
//                intent.putExtra("todo" , todo)
//                startActivity(intent)
//                Log.e("MainActivity" , "return value${todo.todoTitle?.get(0)}")
//            }
//
//        }


    }

    private fun showBottomSheet() {
        dataBinding.floatingActionBottom.setOnClickListener {
            val bottomSheet = AddBottomSheetFragment()
            bottomSheet.show(supportFragmentManager , "")
        }
    }

    private fun showBottomNavigationItem() {
        dataBinding.bottomNavigation.setOnItemSelectedListener {
            var item = it.itemId
            when(item){
                R.id.icon_list ->{
                    showFragment(listFragment)
                }
                R.id.icon_settings ->{
                    showFragment(settingsFragment)
                }
            }

            return@setOnItemSelectedListener true
        }

    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container , fragment).commit()
    }

}