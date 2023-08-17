package versionworkflow.model;

import versionworkflow.model.CustomerInfoDatabase;

public interface CustomerInfoDatabase {
  CustomerInfo get(String customerId);
}
