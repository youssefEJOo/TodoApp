package com.route.todoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.route.todoapp.R
import com.route.todoapp.database.MyDataBase
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



    }

    private fun showBottomSheet() {
        dataBinding.floatingActionBottom.setOnClickListener {
            val bottomSheet = AddBottomSheetFragment()
            bottomSheet.onClickListener = object : AddBottomSheetFragment.OnClickListener{
                override fun onClickToRefresh() {

                }
            }
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