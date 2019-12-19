package com.arysugiarto.submisi.footballmatchschedule.ui.fragment.kalsemenfragment

import com.arysugiarto.submisi.footballmatchschedule.entity.repository.KalsemenRepository
import com.arysugiarto.submisi.footballmatchschedule.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class KalsemenPresenter (
    val view: KalsemenView.View,
    val kalsemenRepository: KalsemenRepository,
    val scheduler: SchedulerProvider
) : KalsemenView.Presenter{
    val compositeDisposable = CompositeDisposable()

    override fun getDataKalsemen(namaLiga: String) {
        view.showLoading()
        compositeDisposable.add(kalsemenRepository.getKalsemen(namaLiga)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe{
                view.showDataKalsemen(it.table)
                view.hideLoading()
            })
    }
    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}