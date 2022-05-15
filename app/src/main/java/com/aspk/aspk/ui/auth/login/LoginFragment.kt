package com.aspk.aspk.ui.auth.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aspk.aspk.R
import com.aspk.aspk.data.local.model.UserEntity
import com.aspk.aspk.data.local.room.UserDatabase
import com.aspk.aspk.databinding.FragmentLoginBinding
import com.aspk.aspk.ui.auth.ContainerAuthFragmentDirections
import com.aspk.aspk.util.SessionManagement
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import splitties.toast.toast
import java.util.regex.Pattern

class LoginFragment: Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var database: UserDatabase
    private val containerNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_main) }
    private val compositeDisposable = CompositeDisposable()
    lateinit var  data : UserEntity
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    lateinit var sessionManagement: SessionManagement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = UserDatabase.getInstance(requireActivity())
        handleClick()
        sessionManagement = SessionManagement(requireActivity())
    }

    private fun handleClick(){
        with(binding){
            btnLogin.setOnClickListener { validationInput() }
        }
    }

    private fun validationInput(){
        with(binding){
            var isValid = true
            var focusView: View? = null

            if (TextUtils.isEmpty(etUsername.text.toString())){
                isValid = false
                focusView = etUsername
                etUsername.error = "Username tidak boleh kosong"
            }
            if (TextUtils.isEmpty(etPassword.text.toString())){
                isValid = false
                focusView = etPassword
                etPassword.error = "Password tidak boleh kosong"
            }

            if (isValid){
                if (checkEmail(etUsername.text.toString())){
                    checkUserByEmail()
                } else {
                    checkUserByUsername()
                }
            } else {
                focusView?.requestFocus()
            }
        }

    }

    private fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    private fun checkUserByEmail() {
        with(binding){
            var isExist = false
            compositeDisposable.add(Completable.fromRunnable { isExist = database.userDao().checkUserExistByEmail(etUsername.text.toString()) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (isExist) {
                        attemptLoginWithEmail()
                    } else {
                        toast("Email Tidak Ditemukan")
                    }
                }
            )
        }
    }

    private fun checkUserByUsername() {
        with(binding){
            var isExist = false
            compositeDisposable.add(Completable.fromRunnable { isExist = database.userDao().checkUserExistByUsername(etUsername.text.toString()) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    if (isExist) {
                        attemptLoginWithUsername()
                    } else {
                        toast("Username Tidak Ditemukan")
                    }
                }
            )
        }
    }

    private fun attemptLoginWithEmail(){
        with(binding) {
            compositeDisposable.add(Completable.fromRunnable { data = database.userDao().getUserByEmail(etUsername.text.toString()) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (data.password == etPassword.text.toString()){
                        toast("Berhasil Login")
                        if (sessionManagement.isFirst){
                            sessionManagement.firstLogin(data.email,data.name)
                        } else {
                            sessionManagement.createAuth(data.email,data.name)
                        }
                        goToHome()
                    }
                },{
                    toast("Gagal melakukan Login")
                })
            )
        }
    }

    private fun attemptLoginWithUsername(){
        with(binding) {
            compositeDisposable.add(Completable.fromRunnable { data = database.userDao().getUserByUsername(etUsername.text.toString()) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (data.password == etPassword.text.toString()){
                        toast("Berhasil Login")
                        if (sessionManagement.isFirst){
                            sessionManagement.firstLogin(data.email,data.name)
                        } else {
                            sessionManagement.createAuth(data.email,data.name)
                        }
                        goToHome()
                    }
                },{
                    toast("Gagal melakukan Login")
                })
            )
        }
    }
    private fun goToHome(){
        val direction = ContainerAuthFragmentDirections.actionContainerAuthFragmentToContainerHomeFragment()
        containerNavController?.navigate(direction)
    }


}