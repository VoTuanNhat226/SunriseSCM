//package com.vtn.components;
//
//import com.vtn.enums.OrderStatus;
//import com.vtn.enums.UserRole;
//import com.vtn.enums.CriteriaType;
//import com.vtn.enums.PaymentTermType;
//import com.vtn.enums.OrderType;
//import com.vtn.enums.DeliveryMethodType;
//import com.vtn.pojo.User;
//import com.vtn.pojo.InventoryDetails;
//import com.vtn.pojo.Category;
//import com.vtn.pojo.Shipper;
//import com.vtn.pojo.Tax;
//import com.vtn.pojo.Rating;
//import com.vtn.pojo.Warehouse;
//import com.vtn.pojo.DeliverySchedule;
//import com.vtn.pojo.Product;
//import com.vtn.pojo.Unit;
//import com.vtn.pojo.Order;
//import com.vtn.pojo.Inventory;
//import com.vtn.pojo.Supplier;
//import com.vtn.pojo.Tag;
//import com.vtn.pojo.Shipment;
//import com.vtn.repository.InventoryDetailsRepository;
//import com.vtn.repository.CategoryRepository;
//import com.vtn.repository.InventoryRepository;
//import com.vtn.repository.RatingRepository;
//import com.vtn.repository.TaxRepository;
//import com.vtn.repository.UnitRepository;
//import com.vtn.repository.ProductRepository;
//import com.vtn.repository.TagRepository;
//import com.vtn.repository.SupplierRepository;
//import com.vtn.repository.WarehouseRepository;
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.vtn.dto.order.OrderDetailsRequest;
//import com.vtn.dto.order.OrderRequest;
//import com.vtn.dto.paymentTerm.PaymentTermsRequest;
//import com.vtn.dto.user.UserRequestRegister;
//import com.vtn.pojo.System;
//import com.vtn.services.OrderService;
//import com.vtn.services.ShipperService;
//import com.vtn.services.UserService;
//import org.hibernate.Session;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.*;
//import java.util.concurrent.ThreadLocalRandom;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//@Component
//@Transactional
//public class GlobalService {
//
//    @Autowired
//    private LocalSessionFactoryBean factory;
//    @Autowired
//    private Cloudinary cloudinary;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private TagRepository tagRepository;
//    @Autowired
//    private UnitRepository unitRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private TaxRepository taxRepository;
//    @Autowired
//    private WarehouseRepository warehouseRepository;
//    @Autowired
//    private InventoryRepository inventoryRepository;
//    @Autowired
//    private InventoryDetailsRepository inventoryDetailsRepository;
//    @Autowired
//    private SupplierRepository supplierRepository;
//    @Autowired
//    private RatingRepository ratingRepository;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private ShipperService shipperService;
//
//    private Session getCurrentSession() {
//        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
//    }
//
//    @SuppressWarnings("rawtypes")
//    public String uploadImage(@NotNull MultipartFile file) {
//        try {
//            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//            return uploadResult.get("secure_url").toString();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public Boolean isFirstRun() {
//        return this.getCurrentSession().get(System.class, 1L) == null;
//    }
//
//    public void saveFirstRun() {
//        Session session = this.getCurrentSession();
//        System system = System.builder().name("isFirstRun").build();
//        session.persist(system);
//    }
//
//    public void createCategory() {
//        List.of(
//                Category.builder().name("Điện thoại").description("Điện thoại").build(),
//                Category.builder().name("Máy tính bảng").description("Máy tính bảng").build(),
//                Category.builder().name("Laptop").description("Laptop").build(),
//                Category.builder().name("Âm thanh").description("Âm thanh").build(),
//                Category.builder().name("Đồng hồ").description("Đồng hồ").build(),
//                Category.builder().name("Camera").description("Camera").build(),
//                Category.builder().name("Đồ gia dụng").description("Đồ gia dụng").build(),
//                Category.builder().name("Phụ kiện").description("Phụ kiện").build(),
//                Category.builder().name("Màn hình").description("Màn hình").build(),
//                Category.builder().name("Máy in").description("Máy in").build()
//        ).forEach(category -> this.categoryRepository.save(category));
//    }
//
//    public void createTag() {
//        List.of(
//                Tag.builder().name("Điện thoại").description("Điện thoại").build(),
//                Tag.builder().name("Máy tính bảng").description("Máy tính bảng").build(),
//                Tag.builder().name("Laptop").description("Laptop").build(),
//                Tag.builder().name("Âm thanh").description("Âm thanh").build(),
//                Tag.builder().name("Đồng hồ").description("Đồng hồ").build(),
//                Tag.builder().name("Camera").description("Camera").build(),
//                Tag.builder().name("Đồ gia dụng").description("Đồ gia dụng").build(),
//                Tag.builder().name("Phụ kiện").description("Phụ kiện").build(),
//                Tag.builder().name("Màn hình").description("Màn hình").build(),
//                Tag.builder().name("Máy in").description("Máy in").build()
//        ).forEach(tag -> this.tagRepository.save(tag));
//
//    }
//
//    public void createUnit() {
//        List.of(
//                Unit.builder().name("Cái").abbreviation("PCS").build(),
//                Unit.builder().name("Hộp").abbreviation("BOX").build(),
//                Unit.builder().name("Gói").abbreviation("PKG").build()
//        ).forEach(unit -> this.unitRepository.save(unit));
//    }
//
//    public void createTax() {
//        List.of(
//                Tax.builder().rate(BigDecimal.valueOf(0.09)).region("VN").build(),
//                Tax.builder().rate(BigDecimal.valueOf(0.07)).region("US").build(),
//                Tax.builder().rate(BigDecimal.valueOf(0.30)).region("EU").build()
//        ).forEach(this.taxRepository::save);
//    }
//
//    public void createWarehouse() {
//        List.of(
//                Warehouse.builder().name("Kho 1").location("TPHCM").capacity(60000000.0F).cost(new BigDecimal(110000)).build(),
//                Warehouse.builder().name("Kho 2").location("Hà Nội").capacity(110000000.0F).cost(new BigDecimal(220000)).build(),
//                Warehouse.builder().name("Kho 3").location("Đà Nẵng").capacity(140000000.0F).cost(new BigDecimal(330000)).build(),
//                Warehouse.builder().name("Kho 4").location("Cần Thơ").capacity(250000000.0F).cost(new BigDecimal(440000)).build(),
//                Warehouse.builder().name("Kho 5").location("Hải Phòng").capacity(260000000.0F).cost(new BigDecimal(550000)).build()
//        ).forEach(this.warehouseRepository::save);
//    }
//
//    public void createUser() {
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("admin@sunrise.com")
//                .username("admin")
//                .password("123456")
//                .userRole(UserRole.ROLE_ADMIN)
//                .build());
//
//        this.createCustomer();
//
//        this.createSupplier();
//
//        this.createShipper();
//    }
//
//    public void createProduct() {
//        AtomicInteger count = new AtomicInteger(1);
//        Random random = new Random();
//
//        List<Category> categories = this.categoryRepository.findAllWithFilter(null);
//        List<Unit> units = this.unitRepository.findAllWithFilter(null);
//        List<Tag> tags = this.tagRepository.findAllWithFilter(null);
//
//        categories.forEach(category -> {
//            // Tạo sản phẩm hết hạn
//            this.createProductsWithExpiryDates(count, random, -30, category, units, tags);
//
//            // Tạo sản phẩm sắp hết hạn
//            this.createProductsWithExpiryDates(count, random, 15, category, units, tags);
//
//            // Tạo sản phẩm còn hạn
//            this.createProductsWithExpiryDates(count, random, 60, category, units, tags);
//        });
//    }
//
//    public void createInventory() {
//        List<Product> products = this.productRepository.findAllWithFilter(null);
//        List<Warehouse> warehouses = this.warehouseRepository.findAllWithFilter(null);
//        Random random = new Random();
//        AtomicInteger count = new AtomicInteger(1);
//
//        warehouses.forEach(warehouse -> IntStream.range(0, 10).forEach(index -> {
//            Inventory inventory = Inventory.builder()
//                    .name("Inventory " + count)
//                    .warehouse(warehouse)
//                    .build();
//
//            Collections.shuffle(products, new Random());
//            int numberOfProductsToReturn = 50 + new Random().nextInt(100 - 50 + 1);
//            List<Product> randomProducts = products.parallelStream()
//                    .limit(numberOfProductsToReturn)
//                    .collect(Collectors.toList());
//
//            Set<InventoryDetails> inventoryDetailsSet = randomProducts.parallelStream()
//                    .map(product -> InventoryDetails.builder()
//                            .quantity(5000 + (random.nextFloat() * (10000 - 100)))
//                            .product(product)
//                            .inventory(inventory)
//                            .build())
//                    .collect(Collectors.toSet());
//
//            inventory.setInventoryDetailsSet(inventoryDetailsSet);
//            this.inventoryRepository.save(inventory);
//
//            count.getAndIncrement();
//        }));
//    }
//
//    public void createRating() {
//        List<CriteriaType> criteriaTypes = new ArrayList<>(List.of(CriteriaType.values()));
//        List<Supplier> suppliers = this.supplierRepository.findAllWithFilter(null);
//        List<User> users = this.userService.findAllWithFilter(null);
//        Random random = new Random();
//        AtomicInteger count = new AtomicInteger(1);
//
//        suppliers.forEach(supplier -> IntStream.range(0, 100).forEach(index -> {
//            Collections.shuffle(criteriaTypes, random);
//            List<User> userList = users.parallelStream()
//                    .filter(u -> u.getSupplier() == null || !u.getSupplier().getId().equals(supplier.getId()))
//                    .collect(Collectors.toList());
//            Collections.shuffle(userList, random);
//
//            Rating rating = Rating.builder()
//                    .rating(BigDecimal.valueOf(1 + (random.nextDouble() * (5 - 1))))
//                    .content("Rating " + count + " for " + supplier.getName())
//                    .user(userList.get(0))
//                    .supplier(supplier)
//                    .criteria(criteriaTypes.get(0))
//                    .build();
//            rating.setCreatedAt(this.getRandomDateTimeInYear());
//
//            this.ratingRepository.save(rating);
//            count.getAndIncrement();
//        }));
//    }
//
//    public void createOrder() {
//        List<InventoryDetails> inventoryDetails = this.inventoryDetailsRepository.findAllWithFilter(null);
//        List<User> users = this.userService.findAllWithFilter(null);
//        Random random = new Random();
//
//        users.forEach(user -> IntStream.range(0, 10).forEach(index -> {
//            Collections.shuffle(inventoryDetails, random);
//            List<Product> randomProducts = inventoryDetails.parallelStream()
//                    .map(InventoryDetails::getProduct)
//                    .limit(3)
//                    .collect(Collectors.toList());
//
//            Set<OrderDetailsRequest> orderDetails = randomProducts.parallelStream()
//                    .map(product -> OrderDetailsRequest.builder()
//                            .productId(product.getId())
//                            .quantity(3F)
//                            .unitPrice(product.getPrice())
//                            .build())
//                    .collect(Collectors.toSet());
//
//            OrderRequest orderRequest = OrderRequest.builder()
//                    .type(OrderType.values()[random.nextInt(OrderType.values().length)])
//                    .status(OrderStatus.DELIVERED)
//                    .paid(true)
//                    .inventoryId(inventoryDetails.get(0).getInventory().getId())
//                    .orderDetails(orderDetails)
//                    .createdAt(this.getRandomDateTimeInYear())
//                    .build();
//
//            if (orderRequest.getType() == OrderType.OUTBOUND) {
//                this.orderService.checkout(user, orderRequest);
//            } else if (orderRequest.getType() == OrderType.INBOUND) {
//                this.orderService.checkin(user, orderRequest);
//            }
//        }));
//    }
//
//    public void createDeliverySchedule() {
//        List<Order> orders = this.orderService.findAllWithFilter(null);
//        List<Warehouse> warehouses = this.warehouseRepository.findAllWithFilter(null);
//        List<Shipper> shippers = this.shipperService.findAllWithFilter(null);
//        Random random = new Random();
//        AtomicInteger count = new AtomicInteger(1);
//
//        orders.forEach(order -> {
//            DeliverySchedule deliverySchedule = DeliverySchedule.builder()
//                    .scheduledDate(LocalDate.now())
//                    .method(DeliveryMethodType.values()[random.nextInt(DeliveryMethodType.values().length)])
//                    .orderSet(Set.of(order))
//                    .build();
//            this.getCurrentSession().save(deliverySchedule);
//
//            Warehouse warehouse = warehouses.get(random.nextInt(warehouses.size()));
//
//            Shipment shipment = Shipment.builder()
//                    .cost(new BigDecimal(1000))
//                    .note("Shipment " + count)
//                    .currentLocation(warehouse.getLocation())
//                    .shipper(shippers.get(random.nextInt(shippers.size())))
//                    .warehouse(warehouse)
//                    .deliverySchedule(deliverySchedule)
//                    .build();
//            this.getCurrentSession().save(shipment);
//
//            order.setDeliverySchedule(deliverySchedule);
//            this.orderService.update(order);
//
//            count.getAndIncrement();
//        });
//    }
//
//    private void createCustomer() {
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("tuancuongKH@sunrise.com")
//                .username("tuancuongKH")
//                .password("123456")
//                .userRole(UserRole.ROLE_CUSTOMER)
//                .firstName("Cuong")
//                .middleName("Tuan")
//                .lastName("Vo")
//                .address("TPHCM")
//                .phone("0123456789")
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("vanminhKH@sunrise.com")
//                .username("vanminhKH")
//                .password("123456")
//                .userRole(UserRole.ROLE_CUSTOMER)
//                .firstName("Minh")
//                .middleName("Ngoc")
//                .lastName("Vo")
//                .address("TPHCM")
//                .phone("0987654321")
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("dieuhienKH@sunrise.com")
//                .username("dieuhienKH")
//                .password("123456")
//                .userRole(UserRole.ROLE_CUSTOMER)
//                .firstName("Hien")
//                .middleName("Dieu")
//                .lastName("Truong")
//                .address("TPHCM")
//                .phone("0775438404")
//                .build());
//    }
//
//    private void createSupplier() {
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("sonduyNCC@sunrise.com")
//                .username("sonduyNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Tran Son Duy")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("quyanNCC@sunrise.com")
//                .username("quyanNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Phan Quy An")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("huudatNCC@sunrise.com")
//                .username("huudatNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Tran Huu Dat")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("vietthangNCC@sunrise.com")
//                .username("vietthangNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Nguyen Viet Thang")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("chitaiNCC@sunrise.com")
//                .username("chitaiNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Le Chi Tai")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("quynhnhuNCC@sunrise.com")
//                .username("quynhnhuNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Nguyen Quynh Nhu")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("nguyentuyenNCC@sunrise.com")
//                .username("nguyentuyenNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Nguyen Tuyen")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("tonghiepNCC@sunrise.com")
//                .username("tonghiepNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Trinh Tong Hiep")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("tandatNCC@sunrise.com")
//                .username("tandatNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Le Tan Dat")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("huytanNCC@sunrise.com")
//                .username("huytanNCC")
//                .password("123456")
//                .userRole(UserRole.ROLE_SUPPLIER)
//                .name("Nguyen Huy Tan")
//                .address("TPHCM")
//                .phone("0987654321")
//                .contactInfo("0987654321")
//                .paymentTermsSet(Set.of(
//                        PaymentTermsRequest.builder()
//                                .discountDays(10)
//                                .discountPercentage(BigDecimal.valueOf(0.03))
//                                .type(PaymentTermType.COD)
//                                .build(),
//                        PaymentTermsRequest.builder()
//                                .discountDays(20)
//                                .discountPercentage(BigDecimal.valueOf(0.05))
//                                .type(PaymentTermType.PREPAID)
//                                .build()
//                ))
//                .build());
//    }
//
//    private void createShipper() {
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("anhvinhSP@sunrise.com")
//                .username("anhvinhSP")
//                .password("123456")
//                .userRole(UserRole.ROLE_SHIPPER)
//                .name("Nguyen Quang Anh Vinh")
//                .contactInfo("0987654321")
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("vuphatSP@sunrise.com")
//                .username("vuphatSP")
//                .password("123456")
//                .userRole(UserRole.ROLE_SHIPPER)
//                .name("Cao Nguyen Vu Phat")
//                .contactInfo("0987654321")
//                .build());
//        this.userService.registerUser(UserRequestRegister.builder()
//                .email("dinhtinhSP@sunrise.com")
//                .username("dinhtinhSP")
//                .password("123456")
//                .userRole(UserRole.ROLE_SHIPPER)
//                .name("Nguyen Dinh Tinh")
//                .contactInfo("0987654321")
//                .build());
//    }
//
//    private @NotNull Date getRandomDateTimeInYear() {
//        // Lấy tháng hiện tại
//        LocalDate now = LocalDate.now();
//        int currentMonth = now.getMonthValue();
//
//        // Random tháng từ 1 đến tháng hiện tại
//        int randomMonth = ThreadLocalRandom.current().nextInt(1, currentMonth + 1);
//
//        // Tạo ngày bắt đầu và ngày kết thúc cho tháng random
//        LocalDate start = LocalDate.of(now.getYear(), randomMonth, 1);
//        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
//
//        // Random ngày trong khoảng từ start đến end
//        long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay() + 1);
//
//        // Chuyển đổi từ epoch day sang LocalDate và sau đó sang Date
//        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
//        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//    }
//
//    @NotNull
//    private Date getDate(long randomDay) {
//        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
//
//        int randomHour = ThreadLocalRandom.current().nextInt(0, 24);
//        int randomMinute = ThreadLocalRandom.current().nextInt(0, 60);
//        int randomSecond = ThreadLocalRandom.current().nextInt(0, 60);
//
//        LocalDateTime randomDateTime = randomDate.atTime(randomHour, randomMinute, randomSecond);
//
//        return Date.from(randomDateTime.atZone(ZoneId.systemDefault()).toInstant());
//    }
//
//    private void createProductsWithExpiryDates(AtomicInteger count, Random random, int daysFromNow, Category
//            category, List<Unit> units, List<Tag> tags) {
//        List<Supplier> suppliers = this.supplierRepository.findAllWithFilter(null);
//
//        suppliers.forEach(supplier -> {
//            for (int i = 0; i < 10; i++) {
//                BigDecimal price = BigDecimal.valueOf(30000 + (random.nextDouble() * (500000 - 50000)));
//
//                LocalDate expiryDate = LocalDate.now().plusDays(daysFromNow);
//
//                Collections.shuffle(tags, random);
//                Set<Tag> randomTags = tags.parallelStream()
//                        .limit(2)
//                        .collect(Collectors.toSet());
//
//                Unit unit = units.get(random.nextInt(units.size()));
//
//                Product product = Product.builder()
//                        .name("Product " + count)
//                        .price(price)
//                        .unit(unit)
//                        .description("Product " + count)
//                        .expiryDate(expiryDate)
//                        .supplier(supplier)
//                        .category(category)
//                        .tagSet(randomTags)
//                        .build();
//                this.productRepository.save(product);
//
//                count.getAndIncrement();
//            }
//        });
//    }
//}


package com.vtn.components;

import com.vtn.enums.OrderStatus;
import com.vtn.enums.UserRole;
import com.vtn.enums.CriteriaType;
import com.vtn.enums.PaymentTermType;
import com.vtn.enums.OrderType;
import com.vtn.enums.DeliveryMethodType;
import com.vtn.pojo.User;
import com.vtn.pojo.InventoryDetails;
import com.vtn.pojo.Category;
import com.vtn.pojo.Shipper;
import com.vtn.pojo.Tax;
import com.vtn.pojo.Rating;
import com.vtn.pojo.Warehouse;
import com.vtn.pojo.DeliverySchedule;
import com.vtn.pojo.Product;
import com.vtn.pojo.Unit;
import com.vtn.pojo.Order;
import com.vtn.pojo.Inventory;
import com.vtn.pojo.Supplier;
import com.vtn.pojo.Tag;
import com.vtn.pojo.Shipment;
import com.vtn.repository.InventoryDetailsRepository;
import com.vtn.repository.CategoryRepository;
import com.vtn.repository.InventoryRepository;
import com.vtn.repository.RatingRepository;
import com.vtn.repository.TaxRepository;
import com.vtn.repository.UnitRepository;
import com.vtn.repository.ProductRepository;
import com.vtn.repository.TagRepository;
import com.vtn.repository.SupplierRepository;
import com.vtn.repository.WarehouseRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vtn.dto.order.OrderDetailsRequest;
import com.vtn.dto.order.OrderRequest;
import com.vtn.dto.paymentTerm.PaymentTermsRequest;
import com.vtn.dto.user.UserRequestRegister;
import com.vtn.pojo.System;
import com.vtn.services.OrderService;
import com.vtn.services.ShipperService;
import com.vtn.services.UserService;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Transactional
public class GlobalService {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TaxRepository taxRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShipperService shipperService;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @SuppressWarnings("rawtypes")
    public String uploadImage(@NotNull MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            return null;
        }
    }

    public Boolean isFirstRun() {
        return this.getCurrentSession().get(System.class, 1L) == null;
    }

    public void saveFirstRun() {
        Session session = this.getCurrentSession();
        System system = System.builder().name("isFirstRun").build();
        session.persist(system);
    }

    public void createCategory() {
        List.of(
                Category.builder().name("Thiết Bị Mạng").description("Thiết Bị Mạng").build(),
                Category.builder().name("Thiết Bị Di Động").description("Thiết Bị Di Động").build(),
                Category.builder().name("Công Nghệ Đám Mây").description("Công Nghệ Đám Mây").build(),
                Category.builder().name("Lưu Trữ và Đám Mây").description("Lưu Trữ và Đám Mây").build(),
                Category.builder().name("An Ninh và Bảo Mật").description("An Ninh và Bảo Mật").build(),
                Category.builder().name("Phần Mềm và Ứng Dụng").description("Phần Mềm và Ứng Dụng").build(),
                Category.builder().name("Máy Tính và Phụ Kiện").description("Máy Tính và Phụ Kiện").build(),
                Category.builder().name("Thiết Bị Đầu Vào và Đầu Ra").description("Thiết Bị Đầu Vào và Đầu Ra").build(),
                Category.builder().name("Phát Triển Web và Ứng Dụng").description("Phát Triển Web và Ứng Dụng").build(),
                Category.builder().name("Khoa Học Dữ Liệu và Trí Tuệ Nhân Tạo").description("Khoa Học Dữ Liệu và Trí Tuệ Nhân Tạo").build()
        ).forEach(category -> this.categoryRepository.save(category));
    }

    public void createTag() {
        List.of(
                Tag.builder().name("Trí Tuệ Nhân Tạo").description("Trí Tuệ Nhân Tạo").build(),
                Tag.builder().name("An Ninh Mạng").description("An Ninh Mạng").build(),
                Tag.builder().name("Điện Toán Đám Mây").description("Điện Toán Đám Mây").build(),
                Tag.builder().name("Khoa Học Dữ Liệu").description("Khoa Học Dữ Liệu").build(),
                Tag.builder().name("Chuỗi Khối").description("Chuỗi Khối").build(),
                Tag.builder().name("Phát Triển và Vận Hành").description("Phát Triển và Vận Hành").build(),
                Tag.builder().name("Học Máy").description("Học Máy").build(),
                Tag.builder().name("Internet Vạn Vật").description("Internet Vạn Vật").build(),
                Tag.builder().name("Dữ Liệu Lớn").description("Dữ Liệu Lớn").build(),
                Tag.builder().name("Phát Triển Phần Mềm").description("Phát Triển Phần Mềm").build()
        ).forEach(tag -> this.tagRepository.save(tag));

    }

    public void createUnit() {
        List.of(
                Unit.builder().name("Cái").abbreviation("PCS").build(),
                Unit.builder().name("Hộp").abbreviation("BOX").build(),
                Unit.builder().name("Kilogram").abbreviation("KG").build(),
                Unit.builder().name("Grams").abbreviation("G").build(),
                Unit.builder().name("Lít").abbreviation("L").build(),
                Unit.builder().name("Mét").abbreviation("M").build(),
                Unit.builder().name("Centimet").abbreviation("CM").build(),
                Unit.builder().name("Gói").abbreviation("PKG").build(),
                Unit.builder().name("Tá").abbreviation("DZ").build(),
                Unit.builder().name("Cuộn").abbreviation("RL").build()
        ).forEach(unit -> this.unitRepository.save(unit));
    }

    public void createTax() {
        List.of(
                Tax.builder().rate(BigDecimal.valueOf(0.01)).region("VN").build(),
                Tax.builder().rate(BigDecimal.valueOf(0.05)).region("US").build(),
                Tax.builder().rate(BigDecimal.valueOf(0.20)).region("EU").build(),
                Tax.builder().rate(BigDecimal.valueOf(0.10)).region("APAC").build(),
                Tax.builder().rate(BigDecimal.valueOf(0.15)).region("LATAM").build(),
                Tax.builder().rate(BigDecimal.valueOf(0.08)).region("MEA").build()
        ).forEach(this.taxRepository::save);
    }

    public void createWarehouse() {
        List.of(
                Warehouse.builder().name("Warehouse 1").location("TPHCM").capacity(50000000.0F).cost(new BigDecimal(100000)).build(),
                Warehouse.builder().name("Warehouse 2").location("Hà Nội").capacity(100000000.0F).cost(new BigDecimal(200000)).build(),
                Warehouse.builder().name("Warehouse 3").location("Đà Nẵng").capacity(150000000.0F).cost(new BigDecimal(300000)).build(),
                Warehouse.builder().name("Warehouse 4").location("Cần Thơ").capacity(200000000.0F).cost(new BigDecimal(400000)).build(),
                Warehouse.builder().name("Warehouse 5").location("Hải Phòng").capacity(250000000.0F).cost(new BigDecimal(500000)).build()
        ).forEach(this.warehouseRepository::save);
    }

    public void createUser() {
        this.userService.registerUser(UserRequestRegister.builder()
                .email("admin@scm.com")
                .username("adminscm")
                .password("admin@123")
                .userRole(UserRole.ROLE_ADMIN)
                .build());

        this.createCustomer();

        this.createSupplier();

        this.createShipper();
    }

    public void createProduct() {
        AtomicInteger count = new AtomicInteger(1);
        Random random = new Random();

        List<Category> categories = this.categoryRepository.findAllWithFilter(null);
        List<Unit> units = this.unitRepository.findAllWithFilter(null);
        List<Tag> tags = this.tagRepository.findAllWithFilter(null);

        categories.forEach(category -> {
            // Tạo sản phẩm hết hạn
            this.createProductsWithExpiryDates(count, random, -30, category, units, tags);

            // Tạo sản phẩm sắp hết hạn
            this.createProductsWithExpiryDates(count, random, 15, category, units, tags);

            // Tạo sản phẩm còn hạn
            this.createProductsWithExpiryDates(count, random, 60, category, units, tags);
        });
    }

    public void createInventory() {
        List<Product> products = this.productRepository.findAllWithFilter(null);
        List<Warehouse> warehouses = this.warehouseRepository.findAllWithFilter(null);
        Random random = new Random();
        AtomicInteger count = new AtomicInteger(1);

        warehouses.forEach(warehouse -> IntStream.range(0, 10).forEach(index -> {
            Inventory inventory = Inventory.builder()
                    .name("Inventory " + count)
                    .warehouse(warehouse)
                    .build();

            Collections.shuffle(products, new Random());
            int numberOfProductsToReturn = 50 + new Random().nextInt(100 - 50 + 1);
            List<Product> randomProducts = products.parallelStream()
                    .limit(numberOfProductsToReturn)
                    .collect(Collectors.toList());

            Set<InventoryDetails> inventoryDetailsSet = randomProducts.parallelStream()
                    .map(product -> InventoryDetails.builder()
                            .quantity(5000 + (random.nextFloat() * (10000 - 100)))
                            .product(product)
                            .inventory(inventory)
                            .build())
                    .collect(Collectors.toSet());

            inventory.setInventoryDetailsSet(inventoryDetailsSet);
            this.inventoryRepository.save(inventory);

            count.getAndIncrement();
        }));
    }

    public void createRating() {
        List<CriteriaType> criteriaTypes = new ArrayList<>(List.of(CriteriaType.values()));
        List<Supplier> suppliers = this.supplierRepository.findAllWithFilter(null);
        List<User> users = this.userService.findAllWithFilter(null);
        Random random = new Random();
        AtomicInteger count = new AtomicInteger(1);

        suppliers.forEach(supplier -> IntStream.range(0, 100).forEach(index -> {
            Collections.shuffle(criteriaTypes, random);
            List<User> userList = users.parallelStream()
                    .filter(u -> u.getSupplier() == null || !u.getSupplier().getId().equals(supplier.getId()))
                    .collect(Collectors.toList());
            Collections.shuffle(userList, random);

            Rating rating = Rating.builder()
                    .rating(BigDecimal.valueOf(1 + (random.nextDouble() * (5 - 1))))
                    .content("Rating " + count + " for " + supplier.getName())
                    .user(userList.get(0))
                    .supplier(supplier)
                    .criteria(criteriaTypes.get(0))
                    .build();
            rating.setCreatedAt(this.getRandomDateTimeInYear());

            this.ratingRepository.save(rating);
            count.getAndIncrement();
        }));
    }

    public void createOrder() {
        List<InventoryDetails> inventoryDetails = this.inventoryDetailsRepository.findAllWithFilter(null);
        List<User> users = this.userService.findAllWithFilter(null);
        Random random = new Random();

        users.forEach(user -> IntStream.range(0, 10).forEach(index -> {
            Collections.shuffle(inventoryDetails, random);
            List<Product> randomProducts = inventoryDetails.parallelStream()
                    .map(InventoryDetails::getProduct)
                    .limit(3)
                    .collect(Collectors.toList());

            Set<OrderDetailsRequest> orderDetails = randomProducts.parallelStream()
                    .map(product -> OrderDetailsRequest.builder()
                            .productId(product.getId())
                            .quantity(3F)
                            .unitPrice(product.getPrice())
                            .build())
                    .collect(Collectors.toSet());

            OrderRequest orderRequest = OrderRequest.builder()
                    .type(OrderType.values()[random.nextInt(OrderType.values().length)])
                    .status(OrderStatus.DELIVERED)
                    .paid(true)
                    .inventoryId(inventoryDetails.get(0).getInventory().getId())
                    .orderDetails(orderDetails)
                    .createdAt(this.getRandomDateTimeInYear())
                    .build();

            if (orderRequest.getType() == OrderType.OUTBOUND) {
                this.orderService.checkout(user, orderRequest);
            } else if (orderRequest.getType() == OrderType.INBOUND) {
                this.orderService.checkin(user, orderRequest);
            }
        }));
    }

    public void createDeliverySchedule() {
        List<Order> orders = this.orderService.findAllWithFilter(null);
        List<Warehouse> warehouses = this.warehouseRepository.findAllWithFilter(null);
        List<Shipper> shippers = this.shipperService.findAllWithFilter(null);
        Random random = new Random();
        AtomicInteger count = new AtomicInteger(1);

        orders.forEach(order -> {
            DeliverySchedule deliverySchedule = DeliverySchedule.builder()
                    .scheduledDate(LocalDate.now())
                    .method(DeliveryMethodType.values()[random.nextInt(DeliveryMethodType.values().length)])
                    .orderSet(Set.of(order))
                    .build();
            this.getCurrentSession().save(deliverySchedule);

            Warehouse warehouse = warehouses.get(random.nextInt(warehouses.size()));

            Shipment shipment = Shipment.builder()
                    .cost(new BigDecimal(1000))
                    .note("Shipment " + count)
                    .currentLocation(warehouse.getLocation())
                    .shipper(shippers.get(random.nextInt(shippers.size())))
                    .warehouse(warehouse)
                    .deliverySchedule(deliverySchedule)
                    .build();
            this.getCurrentSession().save(shipment);

            order.setDeliverySchedule(deliverySchedule);
            this.orderService.update(order);

            count.getAndIncrement();
        });
    }

    private void createCustomer() {
        this.userService.registerUser(UserRequestRegister.builder()
                .email("customer1@scm.com")
                .username("customer1")
                .password("user@123")
                .userRole(UserRole.ROLE_CUSTOMER)
                .firstName("CUSTOMER 1")
                .middleName("M")
                .lastName("L")
                .address("TPHCM")
                .phone("0123456789")
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("customer2@scm.com")
                .username("customer2")
                .password("user@123")
                .userRole(UserRole.ROLE_CUSTOMER)
                .firstName("CUSTOMER 2")
                .middleName("M")
                .lastName("L")
                .address("TPHCM")
                .phone("9872635196")
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("customer3@scm.com")
                .username("customer3")
                .password("user@123")
                .userRole(UserRole.ROLE_CUSTOMER)
                .firstName("CUSTOMER 3")
                .middleName("M")
                .lastName("L")
                .address("TPHCM")
                .phone("2781764019")
                .build());
    }

    private void createSupplier() {
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier1@scm.com")
                .username("supplier1")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 1")
                .address("TPHCM")
                .phone("1234567890")
                .contactInfo("1234567890")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier2@scm.com")
                .username("supplier2")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 2")
                .address("TPHCM")
                .phone("5982716231")
                .contactInfo("5982716231")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier3@scm.com")
                .username("supplier3")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 3")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier4@scm.com")
                .username("supplier4")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 4")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier5@scm.com")
                .username("supplier5")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 3")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier6@scm.com")
                .username("supplier6")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 6")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier7@scm.com")
                .username("supplier7")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 7")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier8@scm.com")
                .username("supplier8")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 8")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier9@scm.com")
                .username("supplier9")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 9")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("supplier10@scm.com")
                .username("supplier10")
                .password("user@123")
                .userRole(UserRole.ROLE_SUPPLIER)
                .name("SUPPLIER 10")
                .address("TPHCM")
                .phone("6782910498")
                .contactInfo("6782910498")
                .paymentTermsSet(Set.of(
                        PaymentTermsRequest.builder()
                                .discountDays(10)
                                .discountPercentage(BigDecimal.valueOf(0.03))
                                .type(PaymentTermType.COD)
                                .build(),
                        PaymentTermsRequest.builder()
                                .discountDays(20)
                                .discountPercentage(BigDecimal.valueOf(0.05))
                                .type(PaymentTermType.PREPAID)
                                .build()
                ))
                .build());
    }

    private void createShipper() {
        this.userService.registerUser(UserRequestRegister.builder()
                .email("shipper1@scm.com")
                .username("shipper1")
                .password("user@123")
                .userRole(UserRole.ROLE_SHIPPER)
                .name("SHIPPER 1")
                .contactInfo("0987654321")
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("shipper2@scm.com")
                .username("shipper2")
                .password("user@123")
                .userRole(UserRole.ROLE_SHIPPER)
                .name("SHIPPER 2")
                .contactInfo("8239184751")
                .build());
        this.userService.registerUser(UserRequestRegister.builder()
                .email("shipper3@scm.com")
                .username("shipper3")
                .password("user@123")
                .userRole(UserRole.ROLE_SHIPPER)
                .name("SHIPPER 3")
                .contactInfo("2617384928")
                .build());
    }

    private @NotNull Date getRandomDateTimeInYear() {
        // Lấy tháng hiện tại
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();

        // Random tháng từ 1 đến tháng hiện tại
        int randomMonth = ThreadLocalRandom.current().nextInt(1, currentMonth + 1);

        // Tạo ngày bắt đầu và ngày kết thúc cho tháng random
        LocalDate start = LocalDate.of(now.getYear(), randomMonth, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Random ngày trong khoảng từ start đến end
        long randomDay = ThreadLocalRandom.current().nextLong(start.toEpochDay(), end.toEpochDay() + 1);

        // Chuyển đổi từ epoch day sang LocalDate và sau đó sang Date
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @NotNull
    private Date getDate(long randomDay) {
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        int randomHour = ThreadLocalRandom.current().nextInt(0, 24);
        int randomMinute = ThreadLocalRandom.current().nextInt(0, 60);
        int randomSecond = ThreadLocalRandom.current().nextInt(0, 60);

        LocalDateTime randomDateTime = randomDate.atTime(randomHour, randomMinute, randomSecond);

        return Date.from(randomDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private void createProductsWithExpiryDates(AtomicInteger count, Random random, int daysFromNow, Category
            category, List<Unit> units, List<Tag> tags) {
        List<Supplier> suppliers = this.supplierRepository.findAllWithFilter(null);

        suppliers.forEach(supplier -> {
            for (int i = 0; i < 10; i++) {
                BigDecimal price = BigDecimal.valueOf(30000 + (random.nextDouble() * (500000 - 50000)));

                LocalDate expiryDate = LocalDate.now().plusDays(daysFromNow);

                Collections.shuffle(tags, random);
                Set<Tag> randomTags = tags.parallelStream()
                        .limit(2)
                        .collect(Collectors.toSet());

                Unit unit = units.get(random.nextInt(units.size()));

                Product product = Product.builder()
                        .name("Product " + count)
                        .price(price)
                        .unit(unit)
                        .description("Product " + count)
                        .expiryDate(expiryDate)
                        .supplier(supplier)
                        .category(category)
                        .tagSet(randomTags)
                        .build();
                this.productRepository.save(product);

                count.getAndIncrement();
            }
        });
    }
}
