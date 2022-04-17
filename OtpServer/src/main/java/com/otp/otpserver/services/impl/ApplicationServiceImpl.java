package com.otp.otpserver.services.impl;

import com.otp.otpserver.model.dao.ApplicationRepository;
import com.otp.otpserver.model.exception.HttpResponseException;
import com.otp.otpserver.model.pojo.Application;
import com.otp.otpserver.services.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    @Override
    public List<Application> getAllApplications() {
        return (List<Application>) applicationRepository.findAll();
    }

    @Override
    public Application getApplication(Integer appId) {
        Optional<Application> app = applicationRepository.findById(appId);

        if(app.isEmpty())
            throw new HttpResponseException("Application does not exist.", HttpStatus.NOT_FOUND);

        return app.get();
    }

    @Override
    public Application deleteApplication(Integer appId) {
        Optional<Application> app = applicationRepository.findById(appId);
        if (app.isEmpty())
            throw new HttpResponseException("Application does not exist.", HttpStatus.NOT_FOUND);

        // Go to all the books of the author and delete

        // TODO: Delete all versions of application
        //bookAuthorRepository.deleteAllByIdAuthor(idAuthor);

        applicationRepository.delete(app.get());
        return app.get();
    }

    @Override
    public Application addApplication(Application application) {
        // Id is not important
        application.setAppId(null);

        // se cauta sa nu fie acelasi nume si prenume
        Application otherApp = (Application) applicationRepository.findByAppName(application.getAppName());

        if (otherApp != null)
            throw new HttpResponseException("An application with same name exists.", HttpStatus.CONFLICT);

        // Se adauga autor
        return applicationRepository.save(application);
    }

    @Override
    public Application updateApplication(Integer appId, Application application) {
        Optional<Application> otherApplicationOptional = applicationRepository.findById(appId);

        if (otherApplicationOptional.isEmpty())
            throw new HttpResponseException("Application does not exist.", HttpStatus.NOT_FOUND);

        Application otherApplication = otherApplicationOptional.get();

        boolean changedName = false;
        // Schimbam elementele care sunt diferite
        if (application.getAppName() != null && !application.getAppName().equals(otherApplication.getAppName())) {
            otherApplication.setAppName(application.getAppName());
            changedName = true;
        }

        // Schimbam elementele care sunt diferite
        if (application.getAppPath() != null && !application.getAppPath().equals(otherApplication.getAppPath())) {
            otherApplication.setAppPath(application.getAppPath());
            changedName = true;
        }

        // Search for other author with the same name
        if(changedName) {
            List<Application> sameNameOther = applicationRepository.findByAppName(otherApplication.getAppName());

            if (!sameNameOther.isEmpty())
                throw new HttpResponseException("An application with this name already exists.", HttpStatus.CONFLICT);
        }

        // Update
        return applicationRepository.save(otherApplication);
    }

    @Override
    public List<Application> getApplicationsByName(String name) {
        return applicationRepository.findByAppName(name);
    }
}
