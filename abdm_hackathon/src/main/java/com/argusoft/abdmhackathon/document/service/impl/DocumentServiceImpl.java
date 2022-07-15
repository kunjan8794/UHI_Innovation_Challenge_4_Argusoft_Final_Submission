package com.argusoft.abdmhackathon.document.service.impl;

import com.argusoft.abdmhackathon.document.service.DocumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * Defines methods for DocumentServiceImpl
 *
 * @author prateek
 * @since 15/07/22 11:25 AM
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    public static final String USER_HOME = "user.home";

    public File getFileByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        String path = System.getProperty(USER_HOME) + "/Repository/";
        File file = new File(path, name);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
