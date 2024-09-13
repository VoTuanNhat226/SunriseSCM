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
import com.vtn.services.CustomerService;
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
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private InventoryDetailsRepository inventoryDetailsRepository;
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


    @NotNull
    private Date getRandomDateTimeInYear() {
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
}
