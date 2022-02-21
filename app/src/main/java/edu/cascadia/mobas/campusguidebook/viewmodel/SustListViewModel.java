package edu.cascadia.mobas.campusguidebook.viewmodel;
import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.ArrayList;
import java.util.List;

import edu.cascadia.mobas.campusguidebook.CampusGuidebookApp;

import edu.cascadia.mobas.campusguidebook.data.model.Sustainability;
import edu.cascadia.mobas.campusguidebook.data.repository.AppRepository;
import edu.cascadia.mobas.campusguidebook.data.repository.ImageRepository;
import edu.cascadia.mobas.campusguidebook.ui.Sustainability.SustUIItem;


@RequiresApi(api = Build.VERSION_CODES.O)
public class SustListViewModel extends AndroidViewModel {

    private AppRepository mAppRepository = null;
    private final MediatorLiveData<List<SustUIItem>> mLiveSustainabilityList = new MediatorLiveData<List<SustUIItem>>();
    private ImageRepository mImageRepository = null;

    public SustListViewModel(@NonNull Application application) {
        super(application);
        mAppRepository = ((CampusGuidebookApp) application).getAppRepository();
        mImageRepository = ((CampusGuidebookApp) application).getImageRepository();

        mLiveSustainabilityList.addSource(mAppRepository.getAllSustainability(), items -> {
            List<SustUIItem> list = new ArrayList<SustUIItem>();
            for (Sustainability sustainability : items) {
                list.add(new SustUIItem(sustainability, mImageRepository.getImage(Sustainability.getImageUri())));
            }
            mLiveSustainabilityList.setValue(list);
        });
    }

}

