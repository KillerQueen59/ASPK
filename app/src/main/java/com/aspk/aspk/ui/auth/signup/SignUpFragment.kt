package com.aspk.aspk.ui.auth.signup

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aspk.aspk.data.local.model.UserEntity
import com.aspk.aspk.data.local.room.UserDatabase
import com.aspk.aspk.databinding.FragmentSignUpBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import splitties.toast.toast

class SignUpFragment: Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var database: UserDatabase

    private val compositeDisposable = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = UserDatabase.getInstance(requireActivity())
        handleClick()
    }

    private fun handleClick(){
        with(binding){
            btnSignUp.setOnClickListener { validationInput() }
        }
    }

    private fun validationInput(){
        with(binding){
            var isValid = true
            var focusView: View? = null
            if (TextUtils.isEmpty(etName.text.toString())){
                isValid = false
                focusView = etName
                etName.error = "Nama tidak boleh kosong"
            }
            if (TextUtils.isEmpty(etEmail.text.toString())){
                isValid = false
                focusView = etEmail
                etEmail.error = "Email tidak boleh kosong"
            }
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

            if (TextUtils.isEmpty(etConfirmPassword.text.toString())){
                isValid = false
                focusView = etConfirmPassword
                etConfirmPassword.error = "Konfirmasi Password tidak boleh kosong"
            }
            if (etConfirmPassword.text.toString() != etPassword.text.toString()){
                isValid = false
                focusView = etConfirmPassword
                etConfirmPassword.error = "Password tidak sama"
            }

            if (isValid){
                checkUserByEmail()
            } else {
                focusView?.requestFocus()
            }
        }

    }

    private fun checkUserByEmail() {
        with(binding){
            var isExist = false
            compositeDisposable.add(Completable.fromRunnable { isExist = database.userDao().checkUserExistByEmail(etEmail.text.toString()) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (!isExist) {
                        checkUserByUsername()
                    } else {
                        toast("Email sudah digunakan")
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
                    if (!isExist) {
                        attemptSignup()
                    } else {
                        toast("Username sudah digunakan")
                    }
                }
            )
        }
    }


    private fun attemptSignup(){
        with(binding) {

            val user = UserEntity(
                name = etName.text.toString(),
                email = etEmail.text.toString(),
                username = etUsername.text.toString(),
                password = etPassword.text.toString()
            )
            compositeDisposable.add(Completable.fromRunnable { database.userDao().addUser(user) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    toast("Berhasil melakukan pendaftaran")
                },{
                    toast("Gagal melakukan pendaftaran")
                })
            )
        }
    }
}