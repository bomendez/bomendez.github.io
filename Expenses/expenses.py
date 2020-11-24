'''
Bo Mendez

This program calculactes business and driving expenses.
'''


def calculate_mileage(start, end):
    '''
        Function -- calculate_mileage
            Calculates miles driven using the start and end odometer values.
        Parameters:
            start -- The odometer reading at the start of the trip. Expecting a
                number greater than 0.
            end -- The odometer reading at the end of the trip. Expecting a
                number greater than 0 and greater than the start value.
        Returns:
            The miles driven, a number. If either parameter is invalid (e.g.
            either parameter is negative or end is less than start), returns 0.
    '''
    if start > 0 and end > start and end > 0:
        mileage = end - start
        return mileage
    else:
        return 0


def get_reimbursement_amount(mileage):
    '''
        Function -- get_reimbursement_amount
            Calculates the amount in dollars that the employee should be
            reimbursed based on their mileage and the standard rate per mile.
            The standard rate for 2020 is 57.5 cents per mile.
        Parameters:
            mileage -- The number of miles driven.
        Returns:
            The amount the employee should be reimbursed in dollars, a float
            rounded to 2 decimal places.

    '''
    RATE = 0.575
    reimbursement = mileage * RATE
    reimbursement = float(round(reimbursement, 2))
    return reimbursement


def get_actual_mileage_rate(mpg, fuel_price):
    '''
        Function -- get_actual_mileage_rate
            Calculates the actual trip cost per mile in dollars based on the
            car's MPG and the fuel price.
        Parameters:
            mpg -- The car's miles per gallon (MPG), an integer greater than 0.
            fuel_price -- The fuel cost in dollars per gallon, a non-negative
            float.
        Returns:
            The actual cost per mile in dollars, a float rounded to 4 decimal
            places. If supplied arguments are invalid, returns 0.0
    '''
    if mpg > 0 and fuel_price >= 0:
        rate = fuel_price / mpg
        rate = float(round(rate, 4))
        return rate
    else:
        return 0.0


def get_actual_trip_cost(start, end, mpg, fuel_price):
    '''
        Function -- get_actual_trip_cost
            Calculates the cost of a trip in dollars based on the miles driven,
            the MPG of the car, and the fuel price per gallon.
        Parameters:
            start -- The odometer reading at the start of the trip. Expecting a
            number greater than 0.
            end -- The odometer reading at the end of the trip. Expecting a
            number greater than 0 and greater than the start value.
            mpg -- The car's miles per gallon (MPG), an integer greater than 0.
            fuel_price -- The fuel price per gallon, a non-negative float.
        Returns:
            The cost of the drive in dollars, a float rounded to 2 decimal
            places. If any of the supplied arguments are invalid, returns 0.0
    '''
    if start > 0 and end > start and end > 0 and mpg > 0 and fuel_price >= 0:
        mileage = calculate_mileage(start, end)
        mileage_rate = get_actual_mileage_rate(mpg, fuel_price)
        cost = round(mileage * mileage_rate, 2)
        return cost
    else:
        return 0.0


def main():
    print("MILEAGE REIMBURSEMENT CALCULATOR")
    print("Options:")
    print("1 - Calculate reimbursement amount from odometer readings")
    print("2 - Calculate reimbursement amount from miles traveled")
    print("3 - Calculate the actual cost of your trip")
    user_choice = input("Enter your choice (1, 2, or 3): ")
    if user_choice == "1" or user_choice == "3":
        start = float(input("Enter your starting odometer reading: "))
        end = float(input("Enter your ending odometer reading: "))
        if user_choice == "1":
            mileage = calculate_mileage(start, end)
            reimbursement = get_reimbursement_amount(mileage)
            print("You will be reimbursed $" + str(reimbursement))
        elif user_choice == "3":
            mpg = int(input("Enter your car's MPG: "))
            fuel_price = float(input("Enter the fuel price per gallon: "))
            total_cost = get_actual_trip_cost(start, end, mpg, fuel_price)
            print("Your trip cost $" + str(total_cost))
    elif user_choice == "2":
        mileage = float(input("Enter the number of miles traveled: "))
        reimbursement = get_reimbursement_amount(mileage)
        print("You will be reimbursed $" + str(reimbursement))
    else:
        print("Not a valid choice")


if __name__ == "__main__":
    main()
