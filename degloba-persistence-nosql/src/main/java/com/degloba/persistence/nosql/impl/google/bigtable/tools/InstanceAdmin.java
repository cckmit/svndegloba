package com.degloba.persistence.nosql.impl.google.bigtable.tools;


import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.bigtable.admin.v2.BigtableInstanceAdminClient;
import com.google.cloud.bigtable.admin.v2.BigtableInstanceAdminSettings;
import com.google.cloud.bigtable.admin.v2.models.Cluster;
import com.google.cloud.bigtable.admin.v2.models.CreateClusterRequest;
import com.google.cloud.bigtable.admin.v2.models.CreateInstanceRequest;
import com.google.cloud.bigtable.admin.v2.models.Instance;
import com.google.cloud.bigtable.admin.v2.models.PartialListInstancesException;
import com.google.cloud.bigtable.admin.v2.models.StorageType;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * <p>Utilitza la classe BigtableInstanceAdminClient per crear, configurar i
 * suprimir instàncies i clústers de grans dimensions.
 *
 * <ul>
 *   <li>crea instància de producció
 *   <li>llista instancies
 *   <li>recupera una instancia
 *   <li>llista clusters
 *   <li>afegeix cluster
 *   <li>esborra cluster
 *   <li>esborra instance
 * </ul>
 */
public class InstanceAdmin {

  private static final String CLUSTER = "cluster";
  private final String clusterId;
  private final String instanceId;
  private final BigtableInstanceAdminClient adminClient;

  public static void main(String[] args) throws IOException {

    if (args.length != 1) {
      System.out.println("Missing required project id");
      return;
    }
    String projectId = args[0];

    InstanceAdmin instanceAdmin =
        new InstanceAdmin(projectId, "ssd-instance", "ssd-cluster");
    instanceAdmin.run();
  }

  /**
   * 
   * @param projectId
   * @param instanceId
   * @param clusterId
   * @throws IOException
   */
  public InstanceAdmin(String projectId, String instanceId, String clusterId)
      throws IOException {
    this.instanceId = instanceId;
    this.clusterId = clusterId;

    // [START connecting_to_bigtable]
    // Crea la configuració per configurar un client d'administrador d'instància BigTable.
    BigtableInstanceAdminSettings instanceAdminSettings =
        BigtableInstanceAdminSettings.newBuilder().setProjectId(projectId).build();

    // Creates a bigtable instance admin client.
    adminClient = BigtableInstanceAdminClient.create(instanceAdminSettings);
    // [END connecting_to_bigtable]
  }

  public void run() {
    createProdInstance();
    listInstances();
    getInstance();
    listClusters();
    addCluster();
    deleteCluster();
    deleteInstance();
    adminClient.close();
  }

  /** Demonstrates how to create a Production instance within a provided project. */
  public void createProdInstance() {
    // Checks if instance exists, creates instance if does not exists.
    if (!adminClient.exists(instanceId)) {
      System.out.println("Instance does not exist, creating a PRODUCTION instance");
      // [START bigtable_create_prod_instance]
      // Creates a Production Instance with the ID "ssd-instance",
      // cluster id "ssd-cluster", 3 nodes and location "us-central1-f".
      CreateInstanceRequest createInstanceRequest =
          CreateInstanceRequest.of(instanceId)
              .addCluster(clusterId, "us-central1-f", 3, StorageType.SSD)
              .setType(Instance.Type.PRODUCTION)
              .addLabel("department", "accounting");
      // Creates a production instance with the given request.
      try {
        Instance instance = adminClient.createInstance(createInstanceRequest);
        System.out.printf("PRODUCTION type instance %s created successfully%n", instance.getId());
      } catch (Exception e) {
        System.err.println("Failed to create instance: " + e.getMessage());
        throw e;
      }
      // [END bigtable_create_prod_instance]
    }
  }

  /** Demonstrates how to list all instances within a project. */
  public void listInstances() {
    System.out.println("\nListing Instances");
    // [START bigtable_list_instances]
    try {
      List<Instance> instances = adminClient.listInstances();
      for (Instance instance : instances) {
        System.out.println(instance.getId());
      }
    } catch (PartialListInstancesException e) {
      System.err.println("Failed to list instances: " + e.getMessage());
      System.err.println("The following zones are unavailable: " + e.getUnavailableZones());
      System.err.println("But the following instances are reachable: " + e.getInstances());
    }
    // [END bigtable_list_instances]
  }

  /** Demonstrates how to get an instance. */
  public Instance getInstance() {
    System.out.println("\nGet Instance");
    // [START bigtable_get_instance]
    Instance instance = null;
    try {
      instance = adminClient.getInstance(instanceId);
      System.out.println("Instance ID: " + instance.getId());
      System.out.println("Display Name: " + instance.getDisplayName());
      System.out.print("Labels: ");
      Map<String, String> labels = instance.getLabels();
      for (String key : labels.keySet()) {
        System.out.printf("%s - %s", key, labels.get(key));
      }
      System.out.println("\nState: " + instance.getState());
      System.out.println("Type: " + instance.getType());
    } catch (NotFoundException e) {
      System.err.println("Failed to get non-existent instance: " + e.getMessage());
    }
    // [END bigtable_get_instance]
    return instance;
  }

  /** Demonstrates how to list clusters within an instance. */
  public void listClusters() {
    System.out.println("\nListing Clusters");
    // [START bigtable_get_clusters]
    try {
      List<Cluster> clusters = adminClient.listClusters(instanceId);
      for (Cluster cluster : clusters) {
        System.out.println(cluster.getId());
      }
    } catch (NotFoundException e) {
      System.err.println("Failed to list clusters from a non-existent instance: " + e.getMessage());
    }
    // [END bigtable_get_clusters]
  }

  /** Demonstrates how to delete an instance. */
  public void deleteInstance() {
    System.out.println("\nDeleting Instance");
    // [START bigtable_delete_instance]
    try {
      adminClient.deleteInstance(instanceId);
      System.out.println("Instance deleted: " + instanceId);
    } catch (NotFoundException e) {
      System.err.println("Failed to delete non-existent instance: " + e.getMessage());
    }
    // [END bigtable_delete_instance]
  }

  /** Demonstrates how to add a cluster to an instance. */
  public void addCluster() {
    System.out.printf("%nAdding cluster: %s to instance: %s%n", CLUSTER, instanceId);
    // [START bigtable_create_cluster]
    try {
      adminClient.createCluster(
          CreateClusterRequest.of(instanceId, CLUSTER)
              .setZone("us-central1-c")
              .setServeNodes(3)
              .setStorageType(StorageType.SSD));
      System.out.printf("Cluster: %s created successfully%n", CLUSTER);
    } catch (AlreadyExistsException e) {
      System.err.println("Failed to add cluster, already exists: " + e.getMessage());
    }
    // [END bigtable_create_cluster]
  }

  /** Demonstrates how to delete a cluster from an instance. */
  public void deleteCluster() {
    System.out.printf("%nDeleting cluster: %s from instance: %s%n", CLUSTER, instanceId);
    // [START bigtable_delete_cluster]
    try {
      adminClient.deleteCluster(instanceId, CLUSTER);
      System.out.printf("Cluster: %s deleted successfully%n", CLUSTER);
    } catch (NotFoundException e) {
      System.err.println("Failed to delete a non-existent cluster: " + e.getMessage());
    }
    // [END bigtable_delete_cluster]
  }
}

