from expenses import (get_actual_trip_cost, get_actual_mileage_rate,
                      get_reimbursement_amount, calculate_mileage)


def test_get_actual_trip_cost():
    assert(get_actual_trip_cost(0, 0, 0, 0) == 0.0)
    assert(get_actual_trip_cost(0, 1, 1, 1) == 0.0)
    assert(get_actual_trip_cost(10, 5, 100, 2) == 0.0)
    assert(get_actual_trip_cost(1000, 1036, 36, 3.09) == 3.09)


def test_get_actual_mileage_rate():
    assert(get_actual_mileage_rate(0, 10) == 0.0)
    assert(get_actual_mileage_rate(100, -10) == 0.0)
    assert(get_actual_mileage_rate(24, 2.99) == 0.1246)


def test_get_reimbursement_amount():
    assert(get_reimbursement_amount(0) == 0.00)
    assert(get_reimbursement_amount(10) == 5.75)


def test_calculate_mileage():
    assert(calculate_mileage(0, 0) == 0)
    assert(calculate_mileage(0, 10) == 0)
    assert(calculate_mileage(10, 9) == 0)
    assert(calculate_mileage(100, 0) == 0)
    assert(calculate_mileage(1000, 1010) == 10)
