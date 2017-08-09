package com.sparrow.supports.message.mail;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by IntelliJ IDEA. User: YZC Date: 13-1-9 Time: 下午7:44 To change this
 * template use File | Settings | File Templates.
 */
public class ImageBuf {
	public static final byte logoPngData[] = new byte[] { -119, 80, 78, 71, 13,
			10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, -36, 0, 0, 0, 51,
			8, 6, 0, 0, 0, -4, 72, 102, -42, 0, 0, 0, 9, 112, 72, 89, 115, 0,
			0, 11, 19, 0, 0, 11, 19, 1, 0, -102, -100, 24, 0, 0, 0, 32, 99, 72,
			82, 77, 0, 0, 122, 37, 0, 0, -128, -125, 0, 0, -7, -1, 0, 0, -128,
			-23, 0, 0, 117, 48, 0, 0, -22, 96, 0, 0, 58, -104, 0, 0, 23, 111,
			-110, 95, -59, 70, 0, 0, 15, -89, 73, 68, 65, 84, 120, -38, -20,
			-99, -49, 111, 27, -57, 21, -57, -7, 7, -11, 16, -96, -79, 37, -43,
			105, 46, -47, -95, 65, -127, 0, 66, 47, 109, 1, 93, -38, 83, -123,
			-100, 117, 104, -113, -62, 30, 9, 37, -74, 40, -121, 38, -41, -108,
			99, 122, -91, 16, -46, 54, -84, 8, -78, -86, -102, 85, 36, 64, 11,
			9, -92, 40, -108, 69, 88, 83, -127, -87, -94, 17, 73, -44, 84, 64,
			-120, 74, 66, 10, -119, -3, 122, 112, -33, 102, 118, 56, -65, 118,
			-71, 100, 44, 103, 7, -40, -125, 72, -18, -52, 82, -100, -49, -66,
			-9, -66, -17, -51, 108, 4, 2, 108, -51, -117, 46, -20, 62, 57,
			-123, -60, 65, 9, 22, -9, 108, -104, -37, -40, 116, 29, 11, -37,
			59, -112, 56, 40, 65, -82, 90, -125, -109, -89, -25, 16, -74, -80,
			-3, -40, 90, 100, -40, 14, 78, -98, -98, -61, -30, -98, 13, 51, 43,
			6, 76, -34, -114, 123, 58, -90, -29, 41, 88, -40, -34, -127, -35,
			39, -89, -31, 47, 17, -74, 16, 56, 81, -53, 85, 107, -66, 32, 19,
			-63, -105, 56, 40, -63, 101, -1, 42, -4, 85, -62, 22, 2, 55, 42,
			-48, 66, -16, -62, 22, 2, -57, -119, -49, -26, 54, 54, 71, 6, 26,
			125, -52, -84, 24, 112, 116, -42, 8, 127, -95, -80, -3, -8, -128,
			-53, 85, 107, 48, 29, 79, -115, 13, 54, -14, 72, 28, -108, -62, 95,
			41, 108, 63, 30, -32, 18, 7, 37, 37, 107, -76, -72, 103, -61, -18,
			-109, 83, 56, 105, -97, 15, 88, -58, -123, -19, -99, -95, -96, 91,
			-40, -34, 9, 93, -52, -80, -67, -6, -64, -55, 64, -103, -37, -40,
			-124, -93, -77, -90, -46, 64, -77, -58, -6, 80, -48, -51, -82, -82,
			-121, -48, -123, -19, -43, 5, 78, 4, -37, -117, -8, -86, -23, 105,
			-96, -75, -29, 10, -28, -86, 53, 56, 58, 107, -70, -6, -98, -37,
			-40, -124, -28, 97, 9, -110, -121, 37, 41, -108, 115, 27, -101,
			-31, 47, 22, -74, 87, 15, -72, 92, -75, 54, 114, -9, 46, 87, -83,
			49, 1, 90, 59, -82, 8, -29, -59, -123, -19, -99, -16, 87, 11, -37,
			-85, 3, -36, -55, -45, 115, -18, 100, 79, 30, -86, 9, 24, -105, -3,
			43, 56, 58, 107, 66, -82, 90, -125, -123, -19, -99, -127, -72, 14,
			27, 47, -31, 125, -46, 62, 23, 90, -69, 92, -75, 22, -2, 114, 97,
			-69, -2, -64, 93, -10, -81, -72, 57, -74, -59, 61, 91, -40, 81,
			-13, -94, 11, -55, -61, 18, -13, 124, 63, -19, -78, 127, -59, -123,
			110, 58, -98, -126, -26, 69, 55, -4, -11, -62, 118, -67, -127, -29,
			41, -110, -13, -71, 45, 33, 24, -55, 67, -79, -110, 41, 114, 43,
			69, -18, -87, 8, -70, 48, -98, 11, -37, -75, 6, -82, 121, -47, -27,
			90, 19, 30, 20, 39, -19, 115, -91, -86, 19, 94, 67, -117, 40, -86,
			-91, 20, 89, -35, 48, 49, 30, -74, 107, 11, 28, 79, -107, -28, -59,
			75, 94, -110, -31, 34, -32, 84, -60, -112, -109, -10, 121, 104,
			-27, -62, -10, -22, 0, 119, -39, -65, -30, -54, -1, 60, -79, -61,
			75, 14, 77, 5, 56, 25, 116, 60, -73, 53, -76, 114, 97, -69, 118,
			-64, -83, 29, 87, -104, -109, 121, -19, -72, -62, -76, 54, 94, -53,
			-68, -68, 64, -60, -125, -114, -25, 90, -122, 105, -126, -80, 93,
			59, -32, 102, 87, -41, 57, -42, 99, 48, -71, 61, 107, -84, -61,
			116, 60, 5, 107, -57, 21, 56, 58, 107, 42, 29, -68, -42, -68, -24,
			50, 63, -49, -117, 25, 121, -7, 65, 89, 94, -16, -24, -84, 49, 112,
			4, -39, 94, 124, -113, -17, -5, 14, 23, -41, -122, -115, 11, 28,
			79, 44, 97, 1, -121, 19, -98, -105, 87, 27, 117, -69, -20, 95, 49,
			-83, 43, 25, 103, 94, -10, -81, -100, -4, -97, 76, -48, -103, 89,
			49, 96, 62, -73, 37, 85, 75, 89, -41, -111, -85, -42, 96, 62, -73,
			37, -76, -10, -77, -85, -21, -80, 118, 92, -15, -99, -62, 56, 58,
			107, 56, 43, -25, -23, 113, 102, 86, 12, -104, -37, -40, 28, -86,
			-1, 87, -79, 117, 58, -99, -79, -116, -45, 106, -75, -72, -17, -11,
			122, 61, 62, 112, -94, -86, 18, -46, -91, 68, 87, 82, -108, 34, 24,
			71, 99, -119, 59, -24, 86, 38, 14, 74, -66, 87, 53, 76, -57, 83,
			-80, -72, 103, 75, -63, -109, 85, -62, -16, 14, -107, -66, -55, 27,
			-101, -41, 53, -121, 115, 27, -101, 67, 91, -42, 88, 44, 6, -7,
			124, -34, -13, 121, -27, 114, 25, 44, -53, -30, -2, -51, -102, -84,
			-11, 122, -35, -45, -127, 19, -68, -45, -23, 12, -68, -121, -112,
			-75, 90, 45, -48, 52, 77, 10, -99, -105, -15, 89, -16, 116, 58, 29,
			-120, -59, 98, -52, -66, 109, -37, 6, -61, 48, -8, -64, -15, -44,
			-55, -103, 21, 3, -110, -121, 37, 88, 59, -82, -72, -42, -63, -87,
			86, -101, -116, -86, -79, 4, -101, -39, -43, 117, -105, 91, -4,
			-58, 82, 2, -34, 121, -16, 8, 126, -69, -74, 1, 19, 75, 9, 120, -3,
			78, 10, 110, 46, -35, 83, 94, -121, -57, -102, -72, -105, -3, -85,
			-95, -41, 3, -14, -6, 14, 114, 12, 86, -36, -83, -46, -86, -43, 42,
			104, -102, 38, -68, 115, -13, 26, 61, -55, 100, -109, -50, 48, 12,
			-120, -59, 98, 96, 24, -122, -46, -127, -97, -59, -66, -55, 115,
			99, -79, 24, -40, -10, -117, -94, -116, 124, 62, 15, -90, 105, 74,
			-81, 87, 117, 124, 77, -45, -96, 94, -81, 15, 124, -41, 124, 62,
			-17, -116, 91, 46, -105, 7, -84, -101, -82, -21, 92, 43, 23, 97,
			-3, -64, 51, 43, -122, -21, 110, 76, -118, 27, 63, -76, -123, 3, 0,
			-31, -124, -5, -39, 82, 18, 102, 30, 125, 4, -97, -100, -42, -95,
			117, -47, -123, 63, 110, 127, 2, 55, -18, 36, 96, -22, -18, 125,
			79, -42, -114, 4, -29, -78, 127, 53, 16, -25, 78, 45, 37, -32, -75,
			-9, -30, -16, -38, -46, 125, -72, -79, -108, -128, -119, 59, -9,
			124, -11, 61, -118, 49, 84, -124, 36, 77, -45, 60, 31, -12, -28,
			27, 6, 56, -124, -124, -9, 62, 9, 62, -39, -97, 109, -37, -82, 115,
			-15, -17, 94, -81, 7, -47, 104, -108, 11, 18, 9, -122, 108, 124,
			-14, 127, 68, 127, 103, 77, -45, -100, 49, -13, -7, 60, 24, -122,
			1, -106, 101, 73, -31, 117, -128, 83, -55, 111, 29, -99, 53, 93,
			-17, -113, 43, 102, -32, -71, 96, -36, 58, -53, 59, -9, -32, 15,
			-71, 45, 104, 126, -35, -123, -85, 103, -33, 65, -23, -117, -1,
			-62, -17, -41, -1, 2, -109, 119, 19, -98, 45, 5, -7, 63, -104, -49,
			109, -71, -58, -104, -120, 37, -32, -35, -20, 95, -31, -35, -51, 2,
			-68, 117, -1, 33, 76, -58, 83, 48, 21, 75, 122, 90, 106, 52, -16,
			-99, 72, -40, 2, 24, 67, 86, 111, 74, -70, 77, 104, -35, 44, -53,
			82, 114, -81, -86, -43, 42, 84, -85, 85, 101, -32, 122, -67, -98,
			-21, -13, -78, 9, 79, 79, 116, 22, 112, -84, 35, 22, -117, 49, -81,
			91, -41, 117, -41, 120, -122, 97, 64, 62, -97, -105, -70, -109, 60,
			-32, 90, -83, 22, -40, -74, 13, -11, 122, -35, -127, 89, 116, 93,
			-28, -40, 76, -32, 102, -115, 117, -95, 58, 56, 29, 79, 57, 75,
			109, 70, -95, 82, 98, -31, -13, 116, 60, -59, 60, -113, 101, -107,
			127, -66, -100, -124, -73, 63, 52, -32, -28, -53, 11, -24, 95, 61,
			-125, 63, -3, -3, 83, -72, 21, 127, 0, -109, 30, 38, 41, 125, -20,
			62, 57, 117, -71, -80, 63, -67, 29, -121, 95, 62, 92, -123, -99,
			-6, -65, -95, -9, -19, 115, 56, -69, -4, 6, -26, -1, -74, 13, -81,
			-57, -18, -61, 84, 44, -31, 123, 37, 59, 89, 82, 23, -44, 24, 94,
			-22, 77, 109, -37, -122, 104, 52, 42, 12, -10, -67, 88, 52, -6,
			-17, 114, -71, 12, -102, -90, 57, -3, 35, 112, -68, -119, -86, 105,
			26, -28, -13, 121, -41, 107, 34, -32, -14, -7, 60, 104, -102, 6,
			-27, 114, -39, 21, -17, -111, -128, -111, -32, 120, 117, 41, 109,
			-37, 118, -6, -60, 24, 81, -45, 52, -88, 86, -85, 66, 75, -50, 116,
			41, 121, 63, -40, 124, 110, 11, 118, -97, -100, 66, -14, 80, 93,
			-120, 72, 30, 126, -65, -7, 79, -13, -94, -21, 88, 6, 47, 121, 56,
			-6, 96, 77, 26, -42, 121, 83, -53, 58, -4, -7, 95, 53, 120, 14, 0,
			-17, 31, 28, -61, -51, 120, 10, -90, -106, -17, -69, 4, 17, -36,
			47, 83, 85, -112, 32, -107, -50, -119, -37, 113, 120, 123, 101, 21,
			-2, -39, -2, 18, -98, -63, 115, 56, -4, 79, 11, -26, -73, 63, -123,
			95, -92, -42, 96, -22, -18, 67, -104, 80, -116, 17, -23, -110, 57,
			82, 121, 13, 122, 12, -98, 107, 73, 79, -84, 104, 52, 10, -47, 104,
			84, 41, -98, 106, -75, 90, -50, -124, -61, 73, 40, 3, -50, 52, 77,
			-105, 24, 35, 2, 46, 26, -115, -6, 2, 14, 69, 26, -38, -102, -15,
			-128, 83, 113, 41, -47, -86, 35, 92, 8, 28, -58, -117, -24, 74,
			-94, -112, 66, 95, 23, -53, 5, -113, 4, -75, -9, 8, 111, 53, -127,
			-88, -4, 74, 5, 56, -106, -123, 100, -99, -9, 86, -30, 33, 60, -3,
			-22, 107, -24, 61, -5, 22, 126, -3, 104, 3, 94, -113, 37, 96, -14,
			14, 127, -91, -72, -54, -74, 15, -28, -115, -26, 39, -73, -105,
			-31, -29, 106, 13, -98, -63, 115, 72, 31, 127, 6, -73, -30, 15,
			-32, -58, -78, 14, -109, -79, 36, -4, -22, -31, 71, -16, -7, -7,
			-105, 3, 55, 26, 21, -127, -125, 44, 58, -112, -115, -79, 85, -5,
			28, -114, -50, 26, -98, -44, 88, -42, 119, 39, 39, -123, 105, -102,
			-114, 59, 41, 115, -117, 108, -37, 118, 20, 64, -45, 52, -99, 73,
			46, 2, 14, -31, 84, -103, -16, -27, 114, 25, 116, 93, -9, -27, 82,
			-30, 88, 104, -87, 73, -91, -110, 6, -50, 52, 77, -48, 117, 93, 73,
			-91, -76, 44, -53, -23, -109, 116, 51, -47, -14, -31, 117, -43,
			-21, 117, 71, 72, -63, -41, 89, -33, 49, 16, -32, 104, 23, -108,
			118, 71, -57, 1, -36, -44, -99, 123, -112, 62, -2, 12, 110, 31,
			-106, -32, -83, -60, 35, -57, -3, 18, -87, -126, -86, -109, -10,
			-58, -5, 31, -64, -17, -42, 11, -48, -2, -90, 11, 95, 92, 124, 5,
			-45, -6, 67, -72, 121, 87, 23, 22, 119, -13, -118, 9, 120, -22,
			-86, 104, 12, -42, 77, 67, -75, -68, 78, 22, -53, -27, -13, 121,
			-48, 117, -35, 87, 42, 32, 26, -115, 74, -127, 99, 9, 40, -84, -55,
			-120, -3, -95, -76, -17, 7, 56, -53, -78, -64, -78, 44, 7, 114,
			-46, -123, 37, -5, -21, -11, 122, -114, -32, 33, 59, 76, -45, 116,
			-50, 69, 55, 19, -33, -45, 117, -35, 5, 28, -3, 63, 24, 25, 112,
			-94, 68, -72, 40, -122, 83, 1, -114, 53, -103, 23, -9, -20, -127,
			-49, 77, -36, -114, -61, 27, 31, -92, -32, 86, -30, -98, 75, 36,
			17, 53, 85, 9, -2, -75, -9, -17, -63, 124, 97, 27, -98, 61, 7, 120,
			-49, 62, -128, -87, -69, 41, -104, -8, 127, 108, -56, -37, 85, -52,
			107, -67, -87, 104, 12, -34, 106, 10, -107, -21, -105, -87, -54,
			-102, -90, 41, -59, 51, 44, -7, 27, 93, 45, 17, 112, -47, 104, 116,
			-32, 92, 26, 56, 18, 54, -65, -94, 9, -114, -123, -106, 77, -41,
			117, 103, 92, -111, -62, -86, -86, -108, -110, -1, 47, -4, -2, 36,
			100, 99, 3, 78, -106, -9, 17, -27, -19, 84, -128, -13, 60, -47,
			-106, 19, -54, -118, -86, -86, -123, -69, -71, -108, -124, 119, 30,
			60, -126, -27, -30, 63, -32, 55, 31, 125, 12, 55, 62, -48, 97, 114,
			73, 110, 65, -67, -4, 31, 121, 99, -16, -124, 35, 81, -87, 27, -19,
			22, -53, 4, 16, -47, -127, 46, 30, 13, 13, -23, 86, -14, -128, -85,
			86, -85, -52, 4, 49, 2, -41, -21, -11, 28, -105, -115, -100, -36,
			42, -64, -103, -90, -23, -64, 78, -58, 114, -104, 24, 39, -29, 70,
			-38, 13, 84, 57, -24, 56, -110, -124, 23, 93, 95, 85, -32, 72, 49,
			42, 50, -52, 46, -54, -78, -27, 49, -51, -117, -82, -16, 7, -105,
			1, -57, -69, 59, 123, -71, 102, -98, 112, -64, 43, -40, -26, 29,
			-73, -106, 117, 120, 51, -79, 2, 111, 38, 62, 116, -27, -12, -126,
			-80, -96, -94, 49, 68, -1, 99, 81, 89, -98, -22, 77, -121, -84,
			-44, 96, -63, -56, -125, -115, -100, 72, 34, 11, -57, 82, 62, 49,
			119, 21, -117, -59, 64, -41, 117, 23, 108, 104, 57, 121, -64, 117,
			58, 29, -89, -46, 3, 99, 74, 116, 67, -15, 64, 119, 15, 93, 94, 22,
			112, -90, 105, -126, 105, -102, -66, -128, 67, 107, 74, -33, 8,
			-56, -22, 26, -38, -14, -30, -7, 17, -65, -107, 13, 42, -78, 51,
			-10, -19, 23, 56, -106, -11, -28, 45, 37, -110, -35, 24, 18, 7, 37,
			-25, -55, 62, -66, -85, 57, -18, -34, 119, 44, -101, -54, 13, -57,
			-41, 126, -100, -44, 24, -78, -115, 112, 85, -6, 20, 21, 107, 91,
			-106, 5, -102, -90, -71, 98, 21, -116, -19, 104, -53, 19, 100, -91,
			9, -83, -30, -111, -106, -110, 78, 81, -48, -3, -95, 64, 17, -115,
			70, 29, 43, 70, -33, 52, 48, 78, 99, -119, 54, -92, 27, 75, -26, 7,
			101, 73, 111, -78, -92, 11, 21, 86, -68, 46, -124, -109, 5, 28,
			-87, 114, 70, 120, -37, 42, -52, 26, -21, 14, 80, 71, 103, -51,
			-127, 100, -77, -20, -119, 55, -92, 5, -15, 3, 28, 79, -116, 80,
			113, -93, 2, 17, -126, 20, 68, 15, 25, 112, 42, -101, -24, 14, -69,
			-13, -76, -54, 117, -54, -36, -2, 78, -89, -29, -28, -78, -48, 58,
			-24, -70, -82, 92, 8, 28, 68, -91, 9, -103, 34, -96, -57, -91, 45,
			38, 74, -1, -83, 86, -53, 5, 29, -17, -69, -15, 74, -42, 48, 63,
			-56, -118, -17, 88, -81, -45, 121, 55, 116, 35, -79, -54, -123,
			-82, -114, 33, -127, -61, -66, 34, 71, 103, 13, 37, 33, -124, 92,
			-113, 38, 43, 29, -94, 87, 104, -5, 1, -114, -105, 102, 96, 73,
			-18, 42, 43, 3, -68, 88, -18, 92, -75, -90, 4, -53, 56, -128, -109,
			-63, -94, 98, -83, 85, -73, -117, 39, -63, -117, -59, 98, 76, 87,
			18, -35, 45, 63, 7, 78, 86, -65, -75, -108, 8, 27, 57, -23, 17, 58,
			-100, -48, -24, -38, -111, -43, 39, 34, -8, -15, 60, -38, -35, 100,
			65, -118, 86, 12, 5, 19, 82, -2, -89, 99, 85, -52, 41, 98, 12,
			-116, 55, -111, 8, 79, 60, 16, 85, -79, 123, -35, -8, -57, 43, 112,
			60, -21, -58, 115, 39, -15, 25, 117, -61, 22, 46, 35, 68, -51,
			-117, -18, 75, 3, -100, 108, -19, 94, -112, -64, -79, -64, 99, -71,
			85, 94, 43, -3, -23, -118, 127, -43, -46, 42, 60, 80, -62, 111,
			-75, 90, 14, 120, -84, 88, 18, -63, 49, 77, -45, -127, -43, -78,
			44, -91, 10, 26, -20, 27, 15, -106, -62, 104, -102, 38, 116, 58,
			29, -24, -11, 122, -50, -25, -16, -6, 104, -41, -76, -41, -21, 57,
			0, -109, -17, 69, 120, -79, 6, 79, -22, -105, -59, 109, 44, -55,
			-34, 43, 112, 60, 119, -107, -11, -7, -23, 120, -54, -71, 97, 4,
			81, -72, -116, 125, -66, 44, 46, -27, 15, 1, 28, 107, 34, -121, 45,
			-104, 22, 121, 17, -93, 53, 2, -39, -70, -128, 46, 114, -10, 3, 28,
			47, -115, -64, 91, 124, 58, -118, -62, -27, -96, 84, -38, 32, -128,
			-109, -59, -54, -93, 4, 46, 108, 35, 2, -114, -9, -61, 121, 121,
			126, -128, 8, 8, 85, -32, 68, -101, -51, -118, -54, -91, 100, -123,
			-53, 11, -37, 59, 78, -66, 76, -43, 93, 124, 89, -128, -109, -63,
			50, 12, 112, -3, 126, 31, 44, -53, -126, 126, -65, 63, -10, -119,
			-41, 110, -73, -95, 80, 40, 56, 2, 13, -21, 58, 42, -107, -118,
			-13, -66, -82, -21, -80, -65, -65, -17, -6, 12, 94, 63, -66, -97,
			78, -89, -95, 82, -87, 92, 15, -32, 88, 86, 78, 22, -81, -87, 2,
			33, 3, 14, -123, 10, -65, 9, 94, 81, -31, 50, 79, 116, 8, 66, -19,
			-108, 1, -57, 114, -81, -125, 6, 78, 69, 44, -30, 89, -55, 98, -79,
			8, -70, -82, -113, 29, -72, 126, -65, 15, -103, 76, -58, 5, 71,
			-95, 80, -128, 108, 54, 59, 0, -37, -29, -57, -113, 29, 64, 51,
			-103, -116, 43, -41, 101, 89, 22, 100, 50, 25, 104, -73, -37, 0, 0,
			-16, -8, -15, 99, -48, 117, -3, -91, -122, 46, 34, -69, 91, -86,
			-20, -3, 40, 43, 99, 18, 1, -121, 34, -123, 72, -15, -108, 85,
			-124, -16, 10, -105, 101, 85, 22, 42, 113, -38, 48, 85, 28, 65, 60,
			49, 86, -74, -59, -4, 48, 113, -32, 15, 5, 28, -126, -127, -96,
			-80, 94, 43, 20, 10, 80, 40, 20, 6, 44, 94, 58, -99, 118, -2, 102,
			89, 52, -42, 121, 47, 45, 112, -68, 7, 121, -56, -30, 57, -36, 84,
			-121, -73, 48, 84, 116, -98, 44, -67, -64, -125, -115, -124, -123,
			87, -72, 60, 14, -105, 47, -120, -46, 49, -65, 86, 84, -12, -32,
			21, 89, 61, 42, -62, -122, 71, -91, 82, 97, 2, -40, 110, -73, 7,
			-84, 6, 78, 124, 60, -73, 80, 40, 56, -96, -32, -126, -49, 74, -91,
			2, -39, 108, -42, -43, -65, 8, 56, 60, 15, 95, 75, -89, -45, 80,
			44, 22, 7, 36, 124, -4, 12, 94, 23, -83, -94, 22, -117, 69, 23,
			-108, -84, 70, -69, -86, -28, -75, 117, -69, 93, -105, -101, -102,
			-51, 102, 93, 99, 96, -1, 100, 31, -39, 108, 22, -38, -19, -74,
			-21, -75, 66, -95, -64, -68, -111, 69, 84, 39, -95, -86, -120, -46,
			-68, -24, 66, -82, 90, 115, 118, -101, -14, -69, 59, -78, 8, -74,
			-7, -36, -106, -21, 58, 121, -123, -53, -29, 0, -114, -25, 10, -85,
			-62, -32, 23, 24, -70, -72, -64, -53, -22, 114, -98, -123, 83, 1,
			14, 97, 67, 48, -6, -3, 62, 100, -77, 89, -56, 100, 50, 46, 40, 72,
			87, 15, -5, -59, -119, -53, 114, 41, 45, -53, 114, 92, -54, 126,
			-65, -49, 116, 13, 73, -56, 104, 64, 105, -104, 120, 86, -101, -66,
			22, -4, 124, -91, 82, 113, -82, -117, -124, 101, 127, 127, 31, 116,
			93, -121, 70, -93, -31, 58, 31, -83, 40, -98, -125, 113, 40, 66,
			-101, 78, -89, 97, 127, 127, 95, 14, -100, -56, 21, -102, 53, -58,
			-13, 20, -46, -35, 39, -89, 92, -40, 48, -82, 100, -62, 66, 21, 46,
			-113, -38, -91, 20, -19, 78, -83, -78, 38, 78, 117, 124, 94, 28,
			-86, 18, -65, -119, 92, 82, 63, -64, -47, 113, 20, 121, 94, -73,
			-37, 117, 89, 56, 50, 102, -45, 117, -35, 101, -79, -16, 115, -28,
			-47, -19, 118, -71, 86, -107, 126, 29, 65, -31, 1, 71, -65, 78,
			-70, -95, 60, -105, 19, -49, -59, -21, -64, -106, -51, 102, -99,
			-101, 1, 126, 87, -78, 127, -76, -120, -28, 121, -28, 13, 68, 10,
			28, 107, 67, 27, -70, 18, 99, 20, -19, -78, 127, 37, 20, 26, -56,
			13, 120, 84, -105, -65, -16, 44, 115, -112, 37, 98, -12, 67, 42,
			85, 11, -93, -23, 29, -47, -68, 108, 60, -92, 42, -56, -120, 86,
			51, -8, 1, 14, 1, 42, 22, -117, 80, 44, 22, 93, -18, 87, -69, -35,
			118, 64, -94, 93, 61, -14, -114, -33, 104, 52, 6, 62, 83, 44, 22,
			33, -109, -55, 64, -73, -37, 29, 25, 112, 56, 46, 79, 84, 41, 20,
			10, -114, -91, -26, -3, -97, 88, -1, 35, -53, -78, 6, -36, 88, -42,
			107, 92, -32, 100, -48, 121, 125, -66, -73, 10, 104, -78, -83, 28,
			88, 59, 105, 121, -79, 66, -119, -125, -110, 115, 120, -79, 108,
			65, -60, 97, -94, 106, 26, 47, -32, -93, -117, -82, 122, -3, 60,
			-21, 59, -84, 75, -119, 18, 124, -79, 88, 116, -59, 126, 50, -32,
			-48, 50, -46, -118, 36, -7, -103, 98, -79, 56, 50, -105, -110, 101,
			125, 121, -106, -116, -41, -25, -56, -128, 83, -127, 14, -35, 76,
			-81, 59, 23, -109, 113, -38, -30, -98, 45, -99, -44, -68, -83, -27,
			124, 85, -29, 123, 116, 23, 71, 85, 44, 77, -54, -3, 65, -43, -127,
			122, 77, -102, -85, 0, 71, 91, 5, 86, 108, -30, 21, -72, 108, 54,
			-53, -36, 40, -106, -4, -116, 72, 52, 105, 52, 26, -66, 68, -109,
			-105, -38, -62, -111, -48, -87, -54, -37, -77, -58, 58, 44, -18,
			-39, -62, -25, 14, 36, 15, 75, 48, -97, -37, 82, -98, 100, -77,
			-85, -21, 92, -73, 8, -41, -37, -115, 10, 56, -108, -45, -125,
			-112, -9, 69, 66, 6, -81, -128, 124, -104, 67, 101, -1, 80, -38,
			26, -80, 98, 24, 50, -73, -59, 115, -11, 48, -127, -19, -59, -62,
			-15, -128, 67, -56, 70, -107, 22, -16, 27, -61, 33, -120, 35, 7,
			46, 72, 69, -49, -49, -92, -111, 89, 78, -81, 91, 25, -8, 81, 31,
			85, 44, -67, 23, -40, 70, -67, -20, -120, 55, -122, 104, -126,
			-111, -126, 7, 78, 122, 76, 54, -45, -94, 9, 57, 97, -55, -12,
			-126, 42, 112, -115, 70, 99, 0, 22, -124, 9, 39, 50, -126, 46, 74,
			124, -17, -17, -17, 123, 78, 124, -29, 119, -58, -21, 107, -73,
			-37, -50, -75, -119, 84, 74, -68, -114, -79, 1, -121, 82, 119, -48,
			119, 123, -98, 43, 39, 115, -121, -24, -21, 10, 114, 121, 14, 107,
			-20, 32, -74, 33, -97, -37, -40, 20, -126, 16, 84, -11, -117, -86,
			123, -113, -110, 62, 9, 25, -99, 95, 34, 101, 115, 26, 66, -108,
			-62, 73, 56, 84, -128, 67, -9, -112, -52, -45, 21, 10, -123, 1,
			-53, 66, -25, 10, -23, -14, 47, 58, 103, -90, -94, -114, -78, -14,
			112, -28, -5, 42, 121, -72, -79, 1, 71, 78, -116, 81, -60, 29, 24,
			51, -7, 21, 94, -42, -114, 43, -66, -81, 11, -123, 21, -39, 100,
			-11, -13, -35, -67, 124, 47, -65, 55, -75, -23, 120, 42, 44, 82,
			-66, 6, 45, 50, -52, -55, 71, 103, 13, 88, -40, -34, 25, 58, -114,
			-62, 71, 70, 5, -43, 78, -98, -98, -61, -38, 113, 5, 22, -74, 119,
			-104, -86, -34, -20, -22, 58, -52, 109, 108, -62, -62, -10, 14,
			-84, 29, 87, 124, 61, 117, -26, -28, -23, -71, -77, 93, 3, 13, 8,
			-10, -97, 56, 40, -7, 126, -94, 13, -82, -15, 19, -71, -78, -8, 52,
			35, -65, -94, 85, -40, -82, 25, 112, 52, 124, 56, 1, 69, 22, 96,
			58, -98, -126, -71, -115, 77, 88, -36, -77, 97, -9, -55, 105, 56,
			81, 60, 88, -16, -16, -127, -113, -41, -65, -3, 111, 0, -91, -29,
			7, -62, -49, 102, 47, -31, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96,
			-126 };

	public static void main(String args[]) {
		// genBytes();
		// System.out.println(zip(pngData).length() + "-" + pngData.length);
	}

	final byte[] unZip(String str) {
		byte[] compressed;
		if (str == null || str.equals(""))
			return null;
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		try {
			compressed = Base64.decodeBase64(str);
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	static String zip(byte bytes[]) {
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;

		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			// zout.setLevel(9);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(bytes);
			zout.closeEntry();
			compressed = out.toByteArray();
		} catch (IOException e) {
			compressed = null;
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		String ret = null;
		if (compressed != null) {
			ret = Base64.encodeBase64String(compressed);
		}
		return ret;
	}

	private static byte imgData[];

	static byte[] readFileBytes(String name) {
		if (imgData != null)
			return imgData;
		InputStream ins = MailSender.class.getResourceAsStream(name);
		byte buf[] = new byte[8192];
		int len = -1;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((len = ins.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			imgData = out.toByteArray();
			return imgData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static void genBytes() {
		InputStream ins = MailSender.class.getResourceAsStream("logo.png");
		byte buf[] = new byte[8192];
		int len = -1;
		try {
			while ((len = ins.read(buf)) > 0) {
				int sp = 0;
				for (int i = 0; i < len; i++) {
					if (sp == 21) {
						sp = 0;
						System.out.println();
					}
					System.out.print(buf[i] + ",");
					sp++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
