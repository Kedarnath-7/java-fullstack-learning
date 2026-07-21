import numpy as np

nums = np.array([1, 2, 3, 4, 5])

# Calculate the mean of the array
mean_value = np.mean(nums)
print("Mean of the array:", mean_value)

# Calculate the standard deviation of the array
std_dev = np.std(nums)
print("Standard Deviation of the array:", std_dev)

print("Original array:", nums)
print("Array after adding 10 to each element:", nums + 10)
print("Array after multiplying each element by 2:", nums * 2)
print(nums.max())
print(nums.min())
print(nums.sum())
print(nums.shape)
print(nums.dtype)
print(nums.ndim)

multi_dimensional_array = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])

print("Multi-dimensional array:\n", multi_dimensional_array)
print(multi_dimensional_array.shape)
print(multi_dimensional_array.ndim)
print("Sum of all elements in the multi-dimensional array:", multi_dimensional_array.sum())
print("Mean of all elements in the multi-dimensional array:", multi_dimensional_array.mean())
print("Standard Deviation of all elements in the multi-dimensional array:", multi_dimensional_array.std())
print("Maximum value in the multi-dimensional array:", multi_dimensional_array.max())
print("Minimum value in the multi-dimensional array:", multi_dimensional_array.min())
