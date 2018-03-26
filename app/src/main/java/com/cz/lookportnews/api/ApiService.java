package com.cz.lookportnews.api;

import com.cz.lookportnews.entity.Channel;
import com.cz.lookportnews.entity.Result;


import io.reactivex.Observable;


/**
 * Created by 14221 on 2018/3/26.
 */

public interface ApiService {





    Observable<Result> getData();
}
